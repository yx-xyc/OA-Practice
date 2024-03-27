class UnionFind:
    def __init__(self, size):
        self.root = list(range(size))
        self.rank = [1] * size

    def find(self, x):
        if x == self.root[x]:
            return x
        self.root[x] = self.find(self.root[x])  # Path compression
        return self.root[x]

    def union(self, x, y):
        rootX = self.find(x)
        rootY = self.find(y)
        if rootX != rootY:
            if self.rank[rootX] > self.rank[rootY]:
                self.root[rootY] = rootX
            elif self.rank[rootX] < self.rank[rootY]:
                self.root[rootX] = rootY
            else:
                self.root[rootY] = rootX
                self.rank[rootX] += 1

    def connected(self, x, y):
        return self.find(x) == self.find(y)


# Initialize UnionFind with 10 elements (0 through 9)
uf = UnionFind(10)

# Connect nodes 1 and 2
uf.union(1, 2)

# Connect nodes 2 and 5
uf.union(2, 5)

# Connect nodes 5 and 6
uf.union(5, 6)

# Connect nodes 3 and 4
uf.union(3, 4)

# Check if nodes 1 and 6 are connected (should be True, as they are part of the same set)
print("Is 1 connected to 6?", uf.connected(1, 6))

# Check if nodes 0 and 9 are connected (should be False, as they have not been connected)
print("Is 0 connected to 9?", uf.connected(0, 9))

# Connect nodes 0 and 9
uf.union(0, 9)

# Check again if nodes 0 and 9 are connected (should now be True)
print("Is 0 connected to 9?", uf.connected(0, 9))

# Check if nodes 1 and 4 are connected (should be False, as they are in different sets)
print("Is 1 connected to 4?", uf.connected(1, 4))

# Connect nodes 6 and 3 (This will connect the two sets together)
uf.union(6, 3)

# Check again if nodes 1 and 4 are connected (should now be True, as their sets have been connected)
print("Is 1 connected to 4?", uf.connected(1, 4))
