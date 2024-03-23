from typing import List
import sys

# Note for optimization
# use memoization to store `calculate_move` and `generate_subsequences` results

def paths_to_a_goal(s: str, n: int, x: int, y: int) -> int:
    MOD = 1000000007
    subsequences = list(generate_subsequences(s))
    ans = 0
    for subsequence in subsequences:
        move = calculate_move(subsequence)
        if move != sys.maxsize and move == y - x:
            ans += 1
            if ans == MOD:
                ans = 0
    return ans

def generate_subsequences(s: str) -> set[str]:
    if len(s) == 0:
        return set([''])
    rest_subsequences = generate_subsequences(s[1:])
    subsequences = []
    for subsequence in rest_subsequences:
        subsequences.append(s[0] + subsequence)
        subsequences.append(subsequence)
    return set(subsequences)

def calculate_move(s: str):
    move = 0
    for c in s:
        if c == 'l':
            move -= 1
        elif c == 'r':
            move += 1
        if move > 0 or move > n:
            return sys.maxsize
    return move

if __name__ == '__main__':
    s = 'rrrlrr'
    n = 7
    x = 0
    y = 0
    print(paths_to_a_goal(s, n, x, y))