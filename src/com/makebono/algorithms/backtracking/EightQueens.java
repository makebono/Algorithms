/**Note:
 * Easy implemetation. Everyone's favorite eight queens problem. Recursively check every possible move, if not legal, cancel it.
 * Until find a solution. Just need to be careful about the value assignments and updates in the diagonal legality checking part.
 * For performance, although arrays at this size won't have this kind of issues, use 1-d array for manipulating.
 * 
 * Instruction:
 *     EightQueens.solve();
 */
package com.makebono.algorithms.backtracking;

import java.util.Arrays;

/** 
 * @ClassName: EightQueens 
 * @Description: Recursion solution of eight queens problem, use back-tracking.
 * @author makebono
 * @date 2017年12月13日 下午5:03:01 
 *  
 */
public class EightQueens {

    // Put the first queen at 0,0 and start solving.
    public static void solve() {
        final int[] entries = new int[64];
        Arrays.fill(entries, 0);
        solve(entries, 0);
        System.out.println("Solution is: ");
        print(entries);
    }

    private static boolean solve(final int[] entries, final int n) {
        if (n > 7) {
            return true;
        } else {
            for (int m = 0; m < 8; m++) {
                if (rowClear(entries, m, n) && diagClear(entries, m, n)) {
                    place(entries, m, n);

                    if (solve(entries, n + 1)) {
                        return true;
                    }

                    remove(entries, m, n);
                }
            }
        }

        return false;
    }

    private static void place(final int[] board, final int m, final int n) {
        board[EightQueens.to1D(m, n)] = 1;
    }

    private static void remove(final int[] board, final int m, final int n) {
        board[EightQueens.to1D(m, n)] = 0;
    }

    // Don't need a colClear() since I'm putting the queens column by column on each row.
    private static boolean rowClear(final int[] board, final int m, final int n) {
        for (int i = 0; i < 8; i++) {
            if (i != n) {
                if (board[EightQueens.to1D(m, i)] == 1) {
                    return false;
                }
            } else {
                continue;
            }
        }
        return true;
    }

    private static boolean diagClear(final int[] board, final int m, final int n) {
        int ulm, urm;
        if (m > 0) {
            ulm = m - 1;
            urm = m - 1;

        } else {
            ulm = m;
            urm = m;
        }

        int llm, lrm;
        if (m < 7) {
            lrm = m + 1;
            llm = m + 1;
        } else {
            lrm = m;
            llm = m;
        }

        int uln, lln;
        if (n > 0) {
            uln = n - 1;
            lln = n - 1;
        } else {
            uln = n;
            lln = n;
        }

        int urn, lrn;
        if (n < 7) {
            urn = n + 1;
            lrn = n + 1;
        } else {
            urn = n;
            lrn = n;
        }

        int count = 0;
        while (count < 7) {
            if (board[EightQueens.to1D(ulm, uln)] == 1 && ulm != m && uln != n) {
                return false;
            }
            if (board[EightQueens.to1D(urm, urn)] == 1 && urm != m && urn != n) {
                return false;
            }
            if (board[EightQueens.to1D(llm, lln)] == 1 && llm != m && lln != n) {
                return false;
            }
            if (board[EightQueens.to1D(lrm, lrn)] == 1 && lrm != m && lrn != n) {
                return false;
            }

            // Be careful about the assignments.
            if (ulm > 0) {
                if (uln > 0) {
                    ulm--;
                    uln--;
                }
            }

            if (urm > 0) {
                if (urn < 7) {
                    urm--;
                    urn++;
                }
            }

            if (llm < 7) {
                if (lln > 0) {
                    llm++;
                    lln--;
                }
            }

            if (lrm < 7) {
                if (lrn < 7) {
                    lrm++;
                    lrn++;
                }
            }

            count++;
        }

        return true;
    }

    public static int to1D(final int m, final int n) {
        return m * 8 + n;
    }

    public static void print(final int[] entries) {
        final StringBuilder sb = new StringBuilder();

        for (int m = 0; m < 8; m++) {
            sb.append("    ");
            for (int n = m * 8; n < m * 8 + 8; n++) {
                sb.append(entries[n] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
