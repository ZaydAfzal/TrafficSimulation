package simulation;

public class Database {

	
	public class EntryPointDb{
		
	};
	
	public class CarParkDb{
		static public CarPark ShoppingCentre = new CarPark(400, RoadsDb.fromCToShoppingCentre);
		static public CarPark IdustrialPark = new CarPark(1000, RoadsDb.fromAToIndustrialPark);
		static public CarPark University = new CarPark(100, RoadsDb.fromDToUniversity);
		static public CarPark Station = new CarPark(150, RoadsDb.fromDToStation); 
	};
	
	public class JunctionDb{
		// duration can be set to some variable that variable will be used for next time
		static public Junction A = new Junction(
				new Road [] {RoadsDb.fromSouthToA,RoadsDb.fromAToIndustrialPark, RoadsDb.fromAToB},
				new int []{500, 1000, 10000}
				);
		static public Junction B = new Junction(
				new Road [] {RoadsDb.fromAToB,RoadsDb.fromCToB, RoadsDb.fromEastToB,RoadsDb.fromBToA, RoadsDb.fromBToC}, // entryPoints
				new int []{500, 1000, 10000}
				);
		static public Junction C = new Junction(
				new Road [] {RoadsDb.fromBToC, RoadsDb.fromNorthToC,RoadsDb.fromCToB, RoadsDb.fromCToD, RoadsDb.fromCToShoppingCentre}, //EntryPoint
				new int []{500, 1000, 10000}
				);
		static public Junction D = new Junction(
				new Road [] {RoadsDb.fromCToD,RoadsDb.fromDToStation, RoadsDb.fromDToUniversity},
				new int []{500, 1000, 10000}
				);
		Junction [] junctions = {A,B,C,D};
		
	}
	
	public class RoadsDb{
		static public Road fromNorthToC = new Road(50, "North", "C");
		static public Road fromEastToB = new Road(30, "East", "B");
		static public Road fromCToD = new Road(10,"C", "D");
		static public Road fromCToB = new Road(10, "C", "B");
		static public Road fromBToC = new Road(10, "B", "C");
		static public Road fromBToA = new Road(7, "B", "A");
		static public Road fromAToB = new Road(7,"A","B");
		static public Road fromSouthToA = new Road(60, "South", "A");
		static public Road fromDToUniversity = new Road(15, "D", "University");
		static public Road fromCToShoppingCentre = new Road(15, "C", "Shopping Centre");
		static public Road fromDToStation = new Road(15, "D", "Station");
		static public Road fromAToIndustrialPark = new Road(15, "A", "Industrial Park");
		
		// not of any use up till now
		final Road [] roads = {
				fromNorthToC,
				fromEastToB,
				fromCToD,
				fromCToB,
				fromBToA,
				fromSouthToA ,
				fromDToUniversity,
				fromCToShoppingCentre,
				fromDToStation ,
				fromAToIndustrialPark ,
		};
		
		public Road find(Road r) {
			for(Road road: roads) {
				if (road==r) {
					return road;
				}
			}
			return null;
		}
	};
	
	
}
