package dev.maxc.com2031.lab5_knapsack;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class GreedyKnapsack {

    /**
     * represents items we want to store in the knapsack
     */
    static class Item {

        /**
         * item name -- for convenience
         */
        private final String name;
        /**
         * value of this item
         */
        private final int value;
        /**
         * weight of this item
         */
        private final int weight;

        /**
         * creates a new item with
         *
         * @param name
         * @param value
         * @param weight
         */

        public Item(final String name, final int value, final int weight) {
            this.weight = weight;
            this.value = value;
            this.name = name;
        }

        /**
         * Convenience method for pretty printing of an item.
         */
        @Override
        public String toString() {
            return "Item [name=" + name + ", value=" + value + ", weight=" + weight + "]";
        }
    }

    /**
     * scaleDown reduces an item to weight w by scaling down the
     * value and weight.  Value rounded down to the nearset integer.
     *
     * @param i the item to be scaled down
     * @param w the weight to be scaled to
     *          returns the scaled down item
     */


    public static Item scaleDown(Item i, int w) {
        Item scaled = new Item(i.name + ".scaled", (i.value * w) / i.weight, w);
        return scaled;
    }

    /**
     * returns the sum of all the values of the iterms in the list
     */

    public static int totalValue(Item[] items) {
        int v = 0;
        for (Item i : items) {
            v = v + i.value;
        }
        return v;
    }

    /**
     * applies a greedy algorithm to the Knapsack problem
     *
     * @param items     - the items already in the order for the greedy algorithm
     * @param maxWeight
     */

    public static Item[] GreedyKnapsack(Item[] items, int maxWeight) {

        int count = 0;
        int remainingWeight = maxWeight;

        Item[] knapsack = new Item[items.length];
        for (Item i : items) {
            // check the items in turn and add them to the knapsack
            // if they can be added
            if (i.weight <= remainingWeight) {
                knapsack[count] = i;
                count++;
                remainingWeight = remainingWeight - i.weight;
            }
        }
        Item[] ans = new Item[count];
        for (int i = 0; i < count; i++) {
            ans[i] = knapsack[i];
        }

        return ans;
    }


//*********************

    /**
     * Generate random set of items.
     */

    public static Item[] generateTest(int numberOfItems, int maxWeight, int maxValue) {

        Random r = new Random();

        String alphabet = "abcdefghijklmnopqrstuvwxyz";

        Item[] test = new Item[numberOfItems];

        for (int j = 0; j < numberOfItems; j++) {
            int weight = r.nextInt(maxWeight) + 1;
            int value = r.nextInt(maxValue) + 1;
            String name = Character.toString(alphabet.charAt(j));
            Item i = new Item(name, value, weight);
            test[j] = i;
        }
        return test;
    }


    /**
     * The main method - run the algorithm on some random data to run
     */

    public static void main(String[] args) {

        Random r = new Random();

        int totalWeight = 0;
        Item[] items = generateTest(6, 20, 10);
        System.out.println("Items to consider: ");
        for (Item i : items) {
            System.out.println(i);
            totalWeight = totalWeight + i.weight;
        }
        int capacity = r.nextInt(totalWeight - 1) + 1;
        System.out.println("Weight capacity: " + capacity + "\n");

        Item[] sack1 = GreedyKnapsack(items, capacity);

        int t1 = totalValue(sack1);

        System.out.println("\nValue from Greedy Algorithm before sorting: " + t1);
        System.out.println("Solution from Greedy Algorithm before sorting:");
        for (Item i : sack1) {
            System.out.println(i);
        }

        // sort for the greedy algorithm
        // The override gives the comparison: if the value is <0 then the first argument is less,
        // if the value is >0 then the second argument is less,
        // and if it is =0 then they are equivalent.
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item arg0, Item arg1) {
                return arg0.weight - arg1.weight;
            }
        });

        // now apply the greedy algorithm to the sorted list
        Item[] sack2 = GreedyKnapsack(items, capacity);

        int t2 = totalValue(sack2);

        System.out.println("\nValue from Greedy Algorithm: " + t2);
        System.out.println("Solution from Greedy Algorithm:");
        for (Item i : sack2) {
            System.out.println(i);
        }

        System.out.println("\n\n");

    }

}
