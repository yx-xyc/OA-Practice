package com.eulerity.hackathon.imagefinder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(
    name = "ImageFinder",
    urlPatterns = {"/main"}
)

public class ImageFinder extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected static final Gson GSON = new GsonBuilder().create();
	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// initialize response
		resp.setContentType("text/json");
		String path = req.getServletPath();
		String url = req.getParameter("url");
		System.out.println("Got request of:" + path + " with query param:" + url);
		// initialize WebCrawler
		url = Utility.encodeURL(url);
		if (Utility.isValidUrl(url)){
			WebCrawler wc = new WebCrawler(url);
			// find all URLs under the same domain
			long startTime = System.currentTimeMillis();
			wc.getAllURLs();
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
			System.out.println("Execution time: " + executionTime + " milliseconds.");
			System.out.println("======================================================================================");
			// get all images on each URL
			startTime = System.currentTimeMillis();
			if (wc.visitedURLs.size()>50) {
				wc.getAllImgsMultiThread();
			} else {
				wc.getAllImgs();
			}
			endTime = System.currentTimeMillis();
			executionTime = endTime - startTime;
			System.out.println("Execution time: " + executionTime + " milliseconds.");
			System.out.println("======================================================================================");
			// report result
			wc.reportResult();
			// convert to string array list to pass to image detector
			List<String> imgSrcs = new ArrayList<>(wc.imgSrcs);
			// detect images that contains person or logo
			startTime = System.currentTimeMillis();
			ImageDetector wd = new ImageDetector();
			wd.processBatchImgSrcs(imgSrcs, "person", wd.personDetectThreshold);
			wd.processBatchImgSrcs(imgSrcs, "logo", 0);
			endTime = System.currentTimeMillis();
			executionTime = endTime - startTime;
			System.out.println("Execution time: " + executionTime + " milliseconds.");
			System.out.println("======================================================================================");
			// add the result to response
			List<List<String>> response = new ArrayList<>();
			response.add(imgSrcs);
			response.add(wd.personImgSrcs);
			response.add(wd.logoImgSrcs);
			resp.getWriter().print(GSON.toJson(response));
		} else {
			// prompt to reenter URL
			String[] res = {"Invalid URL, please try again with other URL."};
			System.out.println(res[0]);
			resp.getWriter().print(GSON.toJson(res));
		}
	}

}
