from typing import Dict, List, Tuple

def fetchItemsToDisplay(items: Dict[str, Tuple[int, int]], sortParameter: int, sortOrder: int, itemsPerPage: int, pageNumber: int)->List[str]:
    orderedItems = [(name, relevance, price) for name, (relevance, price) in items.items()]
    orderedItems.sort(key=lambda x: x[sortParameter], reverse= sortOrder==1)
    startIndex = itemsPerPage * pageNumber
    return [name for name, relevance, price in orderedItems[startIndex:startIndex+itemsPerPage]]

if __name__ == "__main__":
    sortParameter = int(input())
    sortOrder = int(input())
    itemsPerPage = int(input())
    pageNumber = int(input())
    itemsNumber = int(input())
    items = {
        item : (int(relevance), int(price)) for _ in range(itemsNumber) for item, relevance, price in [input().split()]
    }
    result = fetchItemsToDisplay(items, sortParameter, sortOrder, itemsPerPage, pageNumber)
    print(' '.join(result))