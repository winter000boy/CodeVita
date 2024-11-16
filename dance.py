def calc_moves(steps):
    dirs = ["up", "down", "left", "right"]
    n = len(steps)
    dp = [[[float('inf')] * 4 for _ in range(4)] for _ in range(n + 1)]

    for x in range(4):
        for y in range(4):
            dp[0][x][y] = 0

    for i in range(1, n + 1):
        step = dirs.index(steps[i - 1])
        for l in range(4):
            for r in range(4):
                if dp[i - 1][l][r] != float('inf'):
                    if step == l or step == r:
                        dp[i][l][r] = min(dp[i][l][r], dp[i - 1][l][r])
                    else:
                        dp[i][l][r] = min(
                            dp[i][l][r],
                            dp[i - 1][step][r] + 1,
                            dp[i - 1][l][step] + 1
                        )

    result = float('inf')
    for l in range(4):
        for r in range(4):
            result = min(result, dp[n][l][r])
    
    return result

if __name__ == "__main__":
    n = int(input().strip())
    steps = [input().strip() for _ in range(n)]
    print(calc_moves(steps))
