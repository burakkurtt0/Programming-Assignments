import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class IMECEPathFinder {
	public int[][] grid;
	public int height, width;
	public int maxFlyingHeight;
	public double fuelCostPerUnit, climbingCostPerUnit;
	public int min;
	public int max;
	//public Point[][] pointGrid;

	public IMECEPathFinder(String filename, int rows, int cols, int maxFlyingHeight, double fuelCostPerUnit,
			double climbingCostPerUnit) {

		grid = new int[rows][cols];
		//pointGrid = new Point[rows][cols];
		this.height = rows;
		this.width = cols;
		this.maxFlyingHeight = maxFlyingHeight;
		this.fuelCostPerUnit = fuelCostPerUnit;
		this.climbingCostPerUnit = climbingCostPerUnit;
		this.min = Integer.MAX_VALUE;
		this.max = 0;

		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					int val = sc.nextInt();
					//Point p = new Point(i,j);
					//p.height = val;
					grid[i][j] = val;
					//pointGrid[i][j] = p;

					if (val < min) {
						min = val;
					}

					if (val > max) {
						max = val;
					}

				}
			}

			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int Scale(int lo, int hi, int num) {
		int range = hi - lo;
		double scaled_num = (double) (num - lo) / range * 255;
		int scaled_int = (int) Math.floor(scaled_num);
		return scaled_int;
	}

	/**
	 * Draws the grid using the given Graphics object.
	 * Colors should be grayscale values 0-255, scaled based on min/max elevation
	 * values in the grid
	 */
	public void drawGrayscaleMap(Graphics g) {
		try {
			FileWriter fw = new FileWriter("grayscaleMap.dat");

			for (int i = 0; i < grid.length; i++) {
				if (i != 0) {
					fw.write("\n");
				}
				for (int j = 0; j < grid[0].length; j++) {
					int value = Scale(min, max, grid[i][j]);
					g.setColor(new Color(value, value, value));
					g.fillRect(j, i, 1, 1);
					fw.write(value + " ");
				}
			}

			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public Point findMinDistance(double[][] distance , boolean[][] visited){
		double minDistance = Double.POSITIVE_INFINITY;
		Point p = new Point(-1, -1);
		for(int i = 0 ; i < distance.length;i++){
			for(int j  = 0 ; j < distance[0].length ; j++){
				if(!visited[i][j] && distance[i][j] < minDistance){
					minDistance = distance[i][j];
					p.x = i;
					p.y = j;
				}
			}
		}
		return p;
	}


	/**
	 * Get the most cost-efficient path from the source Point start to the
	 * destination Point end
	 * using Dijkstra's algorithm on pixels.
	 * 
	 * @return the List of Points on the most cost-efficient path from start to end
	 */
	public List<Point> getMostEfficientPath(Point start, Point end) {
		int nrow = grid.length;
		int ncol = grid[0].length;
		
		List<Point> path = new ArrayList<>();
		
		PriorityQueue<PathPoint> queue = new PriorityQueue<>(Comparator.comparingDouble(p -> p.cost));
		double[][] cost = new double[nrow][ncol];
		Point[][] prev = new Point[nrow][ncol];
		for (int i = 0; i < nrow; i++) {
            Arrays.fill(cost[i], Double.POSITIVE_INFINITY);
        }

		cost[start.y][start.x] = 0;
		PathPoint startPoint = new PathPoint(start.x, start.y);
		startPoint.cost = 0;
		queue.offer(startPoint);
		while(!queue.isEmpty()){
			PathPoint curr = queue.poll();

			if(curr.x == end.x && curr.y == end.y){
				break;
			}

			double[] neighbours = calculateNeighbours(curr.x, curr.y);
			int[] y_c = {-1,-1,-1,0,0,1,1,1};
			int[] x_c = {-1,0,1,-1,1,-1,0,1};

			for(int i = 0 ; i < 8 ; i++){
				int nx = curr.x + x_c[i];
				int ny = curr.y + y_c[i];

				if(nx >= 0 && nx < ncol && ny >= 0 && ny < nrow && neighbours[i] != -1){
					double newCost = cost[curr.y][curr.x] + neighbours[i];

					if(newCost < cost[ny][nx]){
						cost[ny][nx] = newCost;
						PathPoint next = new PathPoint(nx, ny);
						next.cost = newCost;
						queue.offer(next);
						prev[ny][nx] = curr;
					}
				}
			}
		}

		Point curr = end;
		while(curr !=null){
			path.add(0, curr);
			curr = prev[curr.y][curr.x];
		}

		return path;
	}

	/**
	 * Calculate the most cost-efficient path from source to destination.
	 * 
	 * @return the total cost of this most cost-efficient path when traveling from
	 *         source to destination
	 */
	public double getMostEfficientPathCost(List<Point> path) {
		double totalCost = 0.0;

		for(int i = 0 ; i < path.size()-1;i++){
			totalCost+= calculateCost(path.get(i).x , path.get(i).y , path.get(i+1).x , path.get(i+1).y);
		}

		return totalCost;
	}

	/**
	 * Draw the most cost-efficient path on top of the grayscale map from source to
	 * destination.
	 */
	public void drawMostEfficientPath(Graphics g, List<Point> path) {
		
		for(int i = 0 ; i < path.size();i++){
			g.setColor(new Color(0,255,0));
			g.fillRect(path.get(i).x, path.get(i).y, 1, 1);
		}
	}




	/**
	 * Find an escape path from source towards East such that it has the lowest
	 * elevation change.
	 * Choose a forward step out of 3 possible forward locations, using greedy
	 * method described in the assignment instructions.
	 * 
	 * @return the list of Points on the path
	 */
	public List<Point> getLowestElevationEscapePath(Point start) {
		List<Point> pathPointsList = new ArrayList<>();
		
		int j = start.x;
		Point curr = start;
		pathPointsList.add(start);
		while(j < grid[0].length-1){
			
			
			j++;
			int u , f, d;
			u = curr.y != 0 ? (int) Math.abs(grid[curr.y-1][curr.x+1] - grid[curr.y][curr.x]) : Integer.MAX_VALUE;
			f = (int) Math.abs(grid[curr.y][curr.x+1] - grid[curr.y][curr.x]);
			d = curr.y != grid.length-1 ? (int) Math.abs(grid[curr.y+1][curr.x+1] - grid[curr.y][curr.x]) : Integer.MAX_VALUE;
			
			
			
			
			Point t = new Point(-1, -1);
			if(f <= u && f <= d ){
				
				t =PickMiddle(curr, t);
			}

			else if(u <= d && u < f){
				
				 t= PickUp(curr, t);
			}
			else{
				
				t= PickDown(curr, t);
			}

			
			curr = t;
			pathPointsList.add(curr);


		}

		return pathPointsList;
	}

	/**
	 * Calculate the escape path from source towards East such that it has the
	 * lowest elevation change.
	 * 
	 * @return the total change in elevation for the entire path
	 */
	public int getLowestElevationEscapePathCost(List<Point> pathPointsList) {
		int totalChange = 0;

		for(int i = 0 ; i < pathPointsList.size()-1;i++){
			totalChange+= (int) Math.abs(grid[pathPointsList.get(i).y][pathPointsList.get(i).x] -grid[pathPointsList.get(i+1).y][pathPointsList.get(i+1).x]) ;
		}

		return totalChange;
	}

	/**
	 * Draw the escape path from source towards East on top of the grayscale map
	 * such that it has the lowest elevation change.
	 */
	public void drawLowestElevationEscapePath(Graphics g, List<Point> pathPointsList) {
		for(int i = 0 ; i < pathPointsList.size();i++){
			g.setColor(new Color(255,255,0));
			g.fillRect(pathPointsList.get(i).x, pathPointsList.get(i).y, 1, 1);
		}
	}

	public double calculateCost(int x1,int y1, int x2, int y2){
		if(x2 < 0 || y2 < 0 || y2 >= grid.length || x2 >= grid[0].length){ // !!
			return Double.POSITIVE_INFINITY;
		}


		double d1 = x2-x1;
		double d2 = y2-y1;
		double euc = Math.sqrt(d1*d1 + d2*d2);

		int h1 = grid[y1][x1];
		int h2 = grid[y2][x2];

		if(h2>maxFlyingHeight){
			return Double.POSITIVE_INFINITY;
		}

		double cost =  (euc * fuelCostPerUnit);


		if(h2> h1){
			cost += climbingCostPerUnit * (h2-h1);
		}
		return cost;
	}

	public double[] calculateNeighbours(int i ,int j){
		/* 
		0 - NW
		1 - N
		2 - NE
		3 - W
		4 - E
		5 - SW
		6 - S
		7 - SE 
		*/
		double[] neighbours = new double[8];
		neighbours[0] = calculateCost(j, j, i-1, j-1);
		neighbours[1] = calculateCost(j, j, i, j-1);
		neighbours[2] = calculateCost(j, j, i+1, j-1);
		neighbours[3] = calculateCost(j, j, i-1, j);
		neighbours[4] = calculateCost(j, j, i+1, j);
		neighbours[5] = calculateCost(j, j, i-1, j+1);
		neighbours[6] = calculateCost(j, j, i, j+1);
		neighbours[7] = calculateCost(j, j, i+1, j+1);
		

		


		return neighbours;
	}

	public Point findDirection(int i , int j , int dir){
		if(dir == 0){ return new Point(i-1, j-1);}
		if(dir == 1){ return new Point(i-1, j);}
		if(dir == 2){ return new Point(i-1, j+1);}
		if(dir == 3){ return new Point(i, j-1);}
		if(dir == 4){ return new Point(i, j+1);}
		if(dir == 5){ return new Point(i+1, j-1);}
		if(dir == 6){ return new Point(i+1, j);}
		else{
			return new Point(i+1, j+1);
		}
	
	}

	public Point PickUp(Point curr, Point t){
		t.x = curr.x+1;
		t.y = curr.y-1;
		return t;
	}
	public Point PickMiddle(Point curr,Point t){
		t.y = curr.y;
		t.x = curr.x+1;
		return t;
	}

	public Point PickDown(Point curr, Point t){
		t.y = curr.y+1;
		t.x = curr.x+1;
		return t;
	}


}
