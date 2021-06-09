package world.pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import world.*;
import world.tiles.*;

public class Pathfinding {

	public static double LastTime = System.currentTimeMillis();
	private static Comparator<Node> nodeSorter = new Comparator<Node>() {
		
		@Override
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) {
				return + 1;
			}
			if (n1.fCost > n0.fCost) {
				return -1;
			}
			return 0;
		}
	};
	
	public static boolean clear() {
		
		if (System.currentTimeMillis() - LastTime >= 1000) {
			return true;
		}
		return false;
	}
	
	public static ArrayList<Node> findPath(World world, Vector2i start, Vector2i end) {
		
		LastTime = System.currentTimeMillis();
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		
		Node current = new Node(start,null,0,getDistance(start,end));
		openList.add(current);
		while(openList.size() > 0 ) {
			
			Collections.sort(openList,nodeSorter);
			current = openList.get(0);
			if ( current.tile.equals(end)) {
				
				ArrayList<Node> path = new ArrayList<Node>();
				while(current.parent != null) {
					
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			
			openList.remove(current);
			closedList.add(current);
			for ( int i = 0; i < 9 ; i++) {
				
				if( i == 4 ) continue;
				
				int x = current.tile.x;
				int y = current.tile.y;
				int x1 = (i%3) - 1;
				int y1 = (i/3) - 1;

				Tile tile = World.tiles[x+x1+((y + y1)*World.WIDTH)];
				
				if (tile == null) continue;
				if (tile instanceof WallTile) continue;
				if (i == 0) {
					
					Tile aux = World.tiles[x + x1 + 1+((y+y1)*World.WIDTH)];
					Tile aux2 = World.tiles[x + x1+((y+y1+1)*World.WIDTH)];
					if( aux instanceof WallTile || aux2 instanceof WallTile) {
						
						continue;
					}
				}
				else if ( i == 2) {
					
					Tile aux = World.tiles[x + x1 - 1+((y+y1)*World.WIDTH)];
					Tile aux2 = World.tiles[x + x1 +((y+y1+1)*World.WIDTH)];
					if( aux instanceof WallTile || aux2 instanceof WallTile) {
						
						continue;
					}
				}
				else if(i == 6) {
					
					Tile aux = World.tiles[x + x1+((y+y1-1)*World.WIDTH)];
					Tile aux2 = World.tiles[x + x1 +1+((y+y1)*World.WIDTH)];
					if( aux instanceof WallTile || aux2 instanceof WallTile) {
						
						continue;
					}
				}
				else if(i == 8) {
					
					Tile aux = World.tiles[x + x1+((y+y1-1)*World.WIDTH)];
					Tile aux2 = World.tiles[x + x1 -1+((y+y1)*World.WIDTH)];
					if( aux instanceof WallTile || aux2 instanceof WallTile) {
						
						continue;
					}
				}
				
				Vector2i tmp = new Vector2i(x+x1,y+y1);
				double gCost = current.gCost + getDistance(current.tile,tmp);
				double hCost = getDistance(tmp,end);
				
				Node node = new Node(tmp,current,gCost,hCost);
				
				if(inList(closedList,tmp) && gCost >= current.gCost) continue;
				
				if(!inList(openList,tmp)) {
					
					openList.add(node);
				}
				else if(gCost < current.gCost) {
					
					openList.remove(current);
					openList.add(node);
				}
			}
		}
		closedList.clear();
		return null;
	}
	
	private static boolean inList(ArrayList<Node> list, Vector2i vector) {
		
		for ( int i = 0; i < list.size(); i++) {
			
			if ( list.get(i).tile.equals(vector)) {
				
				return true;
			}
		}
		return false;
	}
	
	private static double getDistance(Vector2i tile, Vector2i goal) {
		
		double dx = tile.x - goal.x,dy = tile.y - goal.y;
		
		return Math.sqrt(dx*dx + dy * dy);
	}
}
