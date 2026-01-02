// DSA winter 2025, Dr. Scobey  -  Liam Ahern A00463807
// Program that reads a formatted text file and
// outputs flights to/from a chosen city,
// plus the distance of said flight
// self asessment - all good

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Collection;

class FlightRoutes {

    // Hold reference to text content
    static TextItems info = new TextItems(
            FlightRoutes.class.getResourceAsStream(
                    "/FlightRoutes.txt")
    );

    // Startup screen data
    static OpeningScreen introScreen = new OpeningScreen(
            "Ahern:Liam:A00463807:u02",
            "Submission 05",
            "Graphs of Airplane Flight Routes"
    );

    private static Graph flightGraph = new Graph();
    private static String fileName;
    private static String command;
    private static String location;

    public static void main(String[] args) {

        if (args.length == 0) {
            introScreen.display();  // Display opening screen and program info
            info.displayItem("ProgramDescription");
            return;
        }

        fileName = args[0];
        command = args[1];
        location = args[2];

        readFile();
        findEdges(location);
    }

    /**
     * method to process file, reads text file line-by-line.
     * adds loations toi a vertex array
     * constructs an undirected edge by parsing values seperated by spaces,
     * then adding to the graph
     */
    private static void readFile() {

        try {

            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            int locationNum = Integer.parseInt(scanner.nextLine().trim());
            Vertex[] locations = new Vertex[locationNum];

            for (int i = 0; i < locationNum; i++) {
                String vertex = scanner.nextLine().trim();
                locations[i] = flightGraph.addVertex(vertex);
            }

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();
                String[] values = line.split("\\s+");

                int vFrom = Integer.parseInt(values[0]);
                int vTo = Integer.parseInt(values[1]);
                int weight = Integer.parseInt(values[2]);

                flightGraph.addUndirectedEdge(locations[vFrom],
                        locations[vTo], weight);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + fileName);
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * takes argument location and finds the associated vertex
     * construct a Collection of edges to/from depending on command-line arg
     * iterates through and prints as per demos formating
     * @param locationName command line argument
     */
    public static void findEdges(String locationName){

        Vertex vertex = flightGraph.getVertex(locationName);

        if (vertex == null){
            System.out.println( locationName + " Not Found");
            return;
        }

        System.out.println("Airport: " + location);
        if(command.equalsIgnoreCase("from")) {
            System.out.println("  Flights from " + location + ":");
            Collection<Edge> edgesFrom = flightGraph.getEdgesFrom(vertex);
            for (Edge edge : edgesFrom) {
                System.out.println("   - " + location + " to "
                        + edge.toVertex.label  + ", "
                        + (int)edge.weight + " miles");
            }
        }

        if(command.equalsIgnoreCase("to")) {

            System.out.println("  Flights to " + location + ":");
            Collection<Edge> edgesTo = flightGraph.getEdgesTo(vertex);
            for (Edge edge : edgesTo) {
                System.out.println("   - " + edge.fromVertex.label + " to "
                        + location +", "
                        + (int)edge.weight + " miles");
            }
        }
    }
}
