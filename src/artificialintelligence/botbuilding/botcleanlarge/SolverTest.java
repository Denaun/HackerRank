package artificialintelligence.botbuilding.botcleanlarge;

import artificialintelligence.botbuilding.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class SolverTest {
    @Test
    void testSolveAStar_sparse() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>(Arrays.asList(new Coordinates(42, 1),
                                                                     new Coordinates(10, 10),
                                                                     new Coordinates(42, 11),
                                                                     new Coordinates(20, 20),
                                                                     new Coordinates(32, 21),
                                                                     new Coordinates(33, 21),
                                                                     new Coordinates(34, 21),
                                                                     new Coordinates(35, 21),
                                                                     new Coordinates(31, 22),
                                                                     new Coordinates(31, 23),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(20, 30),
                                                                     new Coordinates(42, 41)));
        Solver solver = new Solver(bot, dirt);
        boolean found = solver.solveAStar(5_000_000_000L);
        Assertions.assertTrue(found);
        Iterable<Coordinates> plan = solver.getSolution();

        List<Coordinates> all = new ArrayList<>();
        plan.iterator().forEachRemaining(all::add);
        Assertions.assertEquals(dirt.size(), all.size());
        Assertions.assertTrue(dirt.containsAll(all));
        Assertions.assertEquals(155, State.getCost(bot, plan));
    }

    @Test
    void testSolveAStar_dense() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dirt.add(new Coordinates(i, j));
            }
        }
        Solver solver = new Solver(bot, dirt);
        boolean found = solver.solveAStar(5_000_000_000L);
        Assertions.assertFalse(found);

        Iterable<Coordinates> plan = solver.getSolution();
        System.out.println(State.getCost(bot, plan));
    }

    @Test
    void testSolveSa_sparse() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>(Arrays.asList(new Coordinates(42, 1),
                                                                     new Coordinates(10, 10),
                                                                     new Coordinates(42, 11),
                                                                     new Coordinates(20, 20),
                                                                     new Coordinates(32, 21),
                                                                     new Coordinates(33, 21),
                                                                     new Coordinates(34, 21),
                                                                     new Coordinates(35, 21),
                                                                     new Coordinates(31, 22),
                                                                     new Coordinates(31, 23),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(31, 24),
                                                                     new Coordinates(20, 30),
                                                                     new Coordinates(42, 41)));
        Solver solver = new Solver(bot, dirt);
        solver.solveSa(10_000_000);
        Iterable<Coordinates> plan = solver.getSolution();

        List<Coordinates> all = new ArrayList<>();
        plan.iterator().forEachRemaining(all::add);
        Assertions.assertEquals(dirt.size(), all.size());
        Assertions.assertTrue(dirt.containsAll(all));
        int cost = State.getCost(bot, plan);
        System.err.println(cost);
        Assertions.assertTrue(cost >= 155);
        Assertions.assertTrue(cost <= 190);
    }

    @Test
    void testSolveSa_dense() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dirt.add(new Coordinates(i, j));
            }
        }
        Solver solver = new Solver(bot, dirt);
        solver.solveSa(10_000_000L);
        Iterable<Coordinates> plan = solver.getSolution();

        List<Coordinates> all = new ArrayList<>();
        plan.iterator().forEachRemaining(all::add);
        Assertions.assertEquals(dirt.size(), all.size());
        Assertions.assertTrue(dirt.containsAll(all));
        int cost = State.getCost(bot, plan);
        System.err.println(cost);
        Assertions.assertTrue(cost >= 5000);
        Assertions.assertTrue(cost <= 8000);
    }

    @Test
    void testSolveAStarPlusSa_dense() {
        Coordinates bot = new Coordinates(0, 0);
        Collection<Coordinates> dirt = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                dirt.add(new Coordinates(i, j));
            }
        }
        Solver solver = new Solver(bot, dirt);
        solver.solveAStar(1_000_000_000L);
        solver.solveSa(10_000_000L);
        Iterable<Coordinates> plan = solver.getSolution();

        List<Coordinates> all = new ArrayList<>();
        plan.iterator().forEachRemaining(all::add);
        Assertions.assertEquals(dirt.size(), all.size());
        Assertions.assertTrue(dirt.containsAll(all));
        int cost = State.getCost(bot, plan);
        System.err.println(cost);
        Assertions.assertTrue(cost >= 5000);
        Assertions.assertTrue(cost <= 8000);
    }
}