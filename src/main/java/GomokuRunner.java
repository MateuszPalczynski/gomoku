import fais.zti.oramus.gomoku.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GomokuRunner {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: GomokuRunner <size> <firstMark> [periodic]");
            return;
        }
        int size = Integer.parseInt(args[0]);
        Mark first = Mark.valueOf(args[1].toUpperCase());
        GomokuBuilder builder = new GomokuBuilder().size(size).firstMark(first);
        if (args.length >= 3 && "periodic".equalsIgnoreCase(args[2])) {
            builder.periodic();
        }
        Game game = builder.build();
        Set<Move> history = new HashSet<>();
        Scanner scanner = new Scanner(System.in);
        Mark current = first;
        while (true) {
            try {
                Move m = game.nextMove(history, current);
                history.add(m);
                System.out.println("AI moves: " + m.position());
                current = (current == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
                System.out.println("Enter move col row (<enter> to let AI move again):");
                String line = scanner.nextLine();
                if (line.isBlank()) continue;
                String[] parts = line.split("\\s+");
                int c = Integer.parseInt(parts[0]);
                int r = Integer.parseInt(parts[1]);
                history.add(new Move(new Position(c, r), current));
                current = (current == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS);
            } catch (TheWinnerIsException e) {
                System.out.println("Winner: " + e.mark);
                break;
            } catch (ResignException e) {
                System.out.println("AI resigns. Winner: " +
                        (current == Mark.CROSS ? Mark.NOUGHT : Mark.CROSS));
                break;
            } catch (WrongBoardStateException e) {
                System.out.println("Wrong board state: " + e.getMessage());
                break;
            }
        }
        scanner.close();
    }
}