package acm2015.f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProblemF {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine = in.readLine().split(" ");
		String[] secondLine = in.readLine().split(" ");

		int numWalkways = Integer.parseInt(firstLine[1]);
		int init = Integer.parseInt(secondLine[0]);
		int goal = Integer.parseInt(secondLine[1]);

		/*
		 * Map containing all the possible walkways (and their reverses).
		 */
		Map<Integer, Set<Destination>> walkways = new HashMap<Integer, Set<Destination>>();
		/*
		 * List of all current non-finished and non-invalid (ie, dead end)
		 * traversals.
		 */
		List<Traversal> traversals = new LinkedList<Traversal>();
		/*
		 * List of all completed (reached goal from inir) traversals.
		 */
		List<Traversal> completeTraversals = new LinkedList<Traversal>();

		for (int i = 0; i < numWalkways; i++) {
			String[] line = in.readLine().split(" ");
			int a = Integer.parseInt(line[0]);
			int b = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);

			/*
			 * If either the start or end of each of the walkways are not
			 * initialised in the map, initialise them, and then add [a -> b]
			 * and [b -> a].
			 */
			if (!walkways.containsKey(a)) {
				walkways.put(a, new HashSet<Destination>());
			}
			if (!walkways.containsKey(b)) {
				walkways.put(b, new HashSet<Destination>());
			}

			walkways.get(a).add(new Destination(b, weight));
			walkways.get(b).add(new Destination(a, weight));
		}

		/*
		 * For all walkways leaving goal, create a traversal rooted at goal
		 * taking that walkway.
		 */
		for (Destination d : walkways.get(init)) {
			if (d.dest != init) {
				traversals.add(new Traversal(init, d.dest, d.weight, goal));
			}
		}

		while (!traversals.isEmpty()) {
			Traversal traversal;
			for (int i = 0; i < traversals.size(); i++) {
				traversal = traversals.get(i);
				if (checkThing(traversal, walkways)) {
					traversals.remove(traversal);
				}

				if (traversal.complete()) {
					traversals.remove(traversal);
					completeTraversals.add(traversal);
				}

				Traversal t = traversal;
				traversals.remove(traversal);

				for (Destination d : walkways.get(traversal.getLast())) {
					if (!t.contains(d.dest)) {
						traversals.add(new Traversal(t, d.dest, d.weight));
					}
				}
			}

		}

		int max = 0;

		for (Traversal g : completeTraversals) {
			max = Math.max(g.getWeight(), max);
		}
		System.out.println(max);

	}

	/*
	 * Returns true if we have been to every station that we can possibly get to
	 * from this position
	 */
	public static boolean checkThing(Traversal t,
			Map<Integer, Set<Destination>> walkways) {
		for (Destination d : walkways.get(t.getLast())) {
			if (!t.contains(d.dest)) {
				return false;
			}
		}
		return true;
	}

	static class Traversal {
		private ArrayList<Integer> traversals;
		private int weight;
		private int goal;

		public Traversal(int init, int next, int weight, int goal) {
			traversals = new ArrayList<Integer>();
			traversals.add(init);
			traversals.add(next);
			this.weight = weight;
			this.goal = goal;
		}

		public Traversal(Traversal t, int next, int weight) {
			this.traversals = new ArrayList<Integer>(t.traversals);
			this.traversals.add(next);
			this.weight = Math.min(t.weight, weight);
		}

		public boolean contains(int i) {
			return traversals.contains(i);
		}

		public boolean complete() {
			return traversals.contains(goal);
		}

		public int getLast() {
			return traversals.get(traversals.size() - 1);
		}

		public int getWeight() {
			return weight;
		}

		public String toString() {
			return "[" + this.weight + "]: " + traversals.toString();
		}
	}

	static class Destination {
		public int dest;
		public int weight;

		Destination(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
	}

}
