package com.eulerity.hackathon.imagefinder;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.ImageAnnotatorSettings;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * An image detector that detects whether there is person or logo appear in the given image source link.
 */
public class ImageDetector {
    /**
     * A string representing the file path to the Google Cloud Platform (GCP) credentials file.
     */
    private String credentialFilePath = "/Users/xuyanchong/Desktop/Hiring_Test/Eulerity/imagefinder/image-detection-382817-c6f9d875f7f7.json";
    /**
     * An InputStream object created from the credentials file path specified above.
     */
    private InputStream credentialsStream = Files.newInputStream(Paths.get(credentialFilePath));
    /**
     * A GoogleCredentials object created from the credentialsStream object.
     */
    private GoogleCredentials credentials = GoogleCredentials.fromStream(this.credentialsStream);
    /**
     * An ImageAnnotatorSettings object that uses the credentials object to authenticate API calls.
     */
    private ImageAnnotatorSettings settings = ImageAnnotatorSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(this.credentials))
            .build();
    /**
     * An integer representing the maximum number of images to be processed per batch
     * when calling the getImgsWithPerson or getImgsWithLogo methods. The default value is 10.
     */
    public int imgSrcBatchSize = 10;
    /**
     * An integer representing the maximum number of bytes to be processed per batch
     * when calling the imageURLToByte method. The default value is 4096.
     */
    public int imgByteBatchSize = 4096;
    /**
     * A double representing the minimum confidence threshold for detecting human faces in an image.
     * The default value is 0.7.
     */
    public double personDetectThreshold = 0.7;
    /**
     * A List of strings that will be populated with image URLs if a human face is detected in the image.
     */
    public List<String> personImgSrcs;
    /**
     * A List of strings that will be populated with image URLs if a logo is detected in the image.
     */
    public List<String> logoImgSrcs;
    /**
     * Default ImageDetector Constructor method that creates an instance of the class.
     * It initializes the personImgSrcs and logoImgSrcs instance variables as empty ArrayLists.
     * @throws IOException if an I/O error occurs
     */
    public ImageDetector() throws IOException {
        this.personImgSrcs = new ArrayList<>();
        this.logoImgSrcs = new ArrayList<>();
    }
    /**
     * A constructor method that creates an instance of the class with user-specified parameters
     * for the credential file path, image batch sizes, and human face detection threshold.
     * It initializes the personImgSrcs and logoImgSrcs instance variables as empty ArrayLists.
     * @throws IOException if an I/O error occurs
     */
    public ImageDetector(String UserCredentialFilePath, int imgSrcBatchSize, int imgByteBatchSize, double personDetectThreshold) throws IOException {
        this.credentialFilePath = UserCredentialFilePath;
        this.credentialsStream = Files.newInputStream(Paths.get(credentialFilePath));
        this.credentials = GoogleCredentials.fromStream(this.credentialsStream);
        this.settings = ImageAnnotatorSettings.newBuilder().setCredentialsProvider(FixedCredentialsProvider.create(this.credentials)).build();
        this.imgSrcBatchSize = imgSrcBatchSize;
        this.imgByteBatchSize = imgByteBatchSize;
        this.personDetectThreshold = personDetectThreshold;
        this.personImgSrcs = new ArrayList<>();
        this.logoImgSrcs = new ArrayList<>();
    }
    /**
     * A method that takes an image URL as a string and returns the image data as a byte array.
     * @param src image url link in string
     */
    public byte[] imageURLToByte (String src) {
        try {
            URL url = new URL(Utility.encodeURL(src));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] byteChunk = new byte[this.imgByteBatchSize];
            int remainingInput;
            try (InputStream is = url.openStream()) {
                while ((remainingInput = is.read(byteChunk)) > 0) {
                    baos.write(byteChunk, 0, remainingInput);
                }
            } catch (IOException | IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
            return baos.toByteArray();
        } catch (MalformedURLException e) {
            System.err.println(e.getMessage());
        }
        return new byte[0];
    }
    /**
     * A method that takes a List of image URLs and a confidence threshold as parameters.
     * It calls the imageURLToByte method to convert the URLs to image data,
     * then sends the data to the Cloud Vision API to detect human faces.
     * If a human face is detected with a confidence level greater than the threshold,
     * the corresponding image URL is added to the personImgSrcs List.
     * @param SRCs List of String that contains image source links
     * @param threshold The confident threshold in detect human faces
     * @throws IOException if an I/O error occurs
     */
    public void getImgsWithPerson(List<String> SRCs, double threshold) throws IOException {
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create(this.settings)) {
            // convert images into list of byte array
            List<byte[]> imgDataSet = new ArrayList<>();
            for (String src : SRCs) {
                imgDataSet.add(imageURLToByte(src));
            }
            // initialize requests
            List<AnnotateImageRequest> requests = new ArrayList<>();
            for (byte[] data : imgDataSet) {
                Image img = Image.newBuilder().setContent(ByteString.copyFrom(data)).build();
                Feature feat = Feature.newBuilder().setType(Feature.Type.FACE_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
                requests.add(request);
            }
            // send requests and receive responses
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            // process the API responses
            for (int i=0;i<responses.size();i++) {
                AnnotateImageResponse res = response.getResponses(i);
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return;
                }
                for (FaceAnnotation annotation : res.getFaceAnnotationsList()) {
                    if (annotation.getDetectionConfidence()>threshold) {
                        this.personImgSrcs.add(SRCs.get(i));
                        break;
                    }
                }
            }
        }
    }
    /**
     * A method that takes a List of image URLs as a parameter.
     * It calls the imageURLToByte method to convert the URLs to image data,
     * then sends the data to the Cloud Vision API to detect logos.
     * If a logo is detected in the image,
     * the corresponding image URL is added to the logoImgSrcs List.
     * @throws IOException if an I/O error occurs
     */
    public void getImgsWithLogo(List<String> SRCs) throws IOException {
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create(this.settings)) {
            // convert images into list of byte array
            List<byte[]> imgDataSet = new ArrayList<>();
            for (String src : SRCs) {
                imgDataSet.add(imageURLToByte(src));
            }
            // initialize requests
            List<AnnotateImageRequest> requests = new ArrayList<>();
            for (byte[] data : imgDataSet) {
                Image img = Image.newBuilder().setContent(ByteString.copyFrom(data)).build();
                Feature feat = Feature.newBuilder().setType(Feature.Type.LOGO_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder().addFeatures(feat).setImage(img).build();
                requests.add(request);
            }
            // send requests and receive responses
            BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
            List<AnnotateImageResponse> responses = response.getResponsesList();
            // process the API responses
            for (int i=0;i<responses.size();i++) {
                AnnotateImageResponse res = response.getResponses(i);
                if (res.hasError()) {
                    System.out.format("Error: %s%n", res.getError().getMessage());
                    return;
                }
                if (res.getLogoAnnotationsList().size()>0) {
                    this.logoImgSrcs.add(SRCs.get(i));
                }
            }
        }
    }

    /**
     * A method that processes a batch of image sources using a specified object detection model and threshold.
     * The image sources are split into batches based on the batch size
     * and the object detection model is applied to each batch.
     * @param SRCs a list of image source URLs to process
     * @param detectObjectType the type of object to detect (either "person" or "logo")
     * @param threshold the threshold value for the detection (only used for person detection)
     * @throws IOException if there is an error reading or processing the image
     */
    public void processBatchImgSrcs(List<String> SRCs, String detectObjectType, double threshold) throws IOException {
        System.out.println("Starting detect image with "+detectObjectType);
        List<List<String>> batches = new ArrayList<>();
        for (int i=0;i<SRCs.size();i+=this.imgSrcBatchSize) {
            int endIndex = Math.min(i+this.imgSrcBatchSize, SRCs.size());
            List<String> batch = SRCs.subList(i, endIndex);
            batches.add(batch);
        }
        if (detectObjectType.equals("person")) {
            for (List<String> batch : batches) {
                getImgsWithPerson(batch, threshold);
            }
        } else if (detectObjectType.equals("logo")) {
            for (List<String> batch :batches) {
                getImgsWithLogo(batch);
            }
        }
        System.out.println("Finishing detect image with "+detectObjectType);
    }
    /**
     * Main method that initializes a ImageDetector test methods above
     * @param args an array of command-line arguments
     * @throws Exception if an error occurs during the web crawling or image detection process
     */
    public static void main(String[] args) throws Exception {
        String url_string = "https://www.sciencekids.co.nz/pictures/scientists.html";
        WebCrawler wc = new WebCrawler(url_string);
        wc.getAllURLs();
        if (wc.visitedURLs.size()>50) {
            wc.getAllImgsMultiThread();
        } else {
            wc.getAllImgs();
        }
        wc.reportResult();
        List<String> imgSrcs = new ArrayList<>(wc.imgSrcs);
        ImageDetector wd = new ImageDetector();
        wd.processBatchImgSrcs(imgSrcs, "person", wd.personDetectThreshold);
        System.out.println("Person Image Srcs: ");
        for (String src : wd.personImgSrcs) {
            System.out.println(src);
        }
        wd.processBatchImgSrcs(imgSrcs, "logo", 0);
        System.out.println("Logo Image Srcs: ");
        for (String src : wd.logoImgSrcs) {
            System.out.println(src);
        }
    }
}



