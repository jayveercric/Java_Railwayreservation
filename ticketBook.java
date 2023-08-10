import java.util.*;
public class ticketBook {
    static int availLB = 1;
    static int availUB = 1;
    static int availMB = 1;
    static int availRAC =1;
    static int availWL = 1;

    static List<Integer> lowerBPos = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> upperBPos = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> middleBPos = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> RACPos = new ArrayList<Integer>(Arrays.asList(1));
    static List<Integer> WLPos = new ArrayList<Integer>(Arrays.asList(1));
    static Queue<Integer> wlList = new LinkedList<Integer>();
    static Queue<Integer> RACList = new LinkedList<Integer>();
    static Queue<Integer> bookedTicketList = new LinkedList<Integer>();
    static Map<Integer,passenger> passenger_data = new HashMap<Integer, passenger>();
    //ArrayList<Integer> keylist = new ArrayList<Integer>(passenger_data.keySet());
    //ArrayList<String> valuelist = new ArrayList<String>(passenger_data.values());

    public void bookTicket(passenger p ,int snumber,String allotedBerth)
    {
        p.number=snumber;
        p.alloted=allotedBerth;
        passenger_data.put(p.passengerId,p);
        bookedTicketList.add(p.passengerId);
        System.out.println("----------------------");
        System.out.println("Passenger ID :" + p.passengerId);
        System.out.println("Passenger Name :" + p.name);
        System.out.println("passenger Age :" + p.age);
        System.out.println("Passenger Gender :" + p.gender);
        System.out.println("Passenger Berth :" +snumber+allotedBerth);
        System.out.println("BOOKED SUCCESSFULLY");
        System.out.println("-----------------------");
    }
    public void racTicket(passenger p ,int snumber , String RACberth )
    {
        p.number=snumber;
        p.alloted=RACberth;
        passenger_data.put(p.passengerId,p);
        RACList.add(p.passengerId);
        System.out.println("-----------------------");
        System.out.println("Passenger ID :" + p.passengerId);
        System.out.println("Passenger Name :" + p.name);
        System.out.println("passenger Age :" + p.age);
        System.out.println("Passenger Gender :" + p.gender);
        System.out.println("Passenger Berth :" +snumber+RACberth);
        System.out.println(" RAC BIRTH GIVEN SUCCESSFULLY");
        System.out.println("-------------------------");
    }
    public void wlTicket(passenger p, int snumber ,String WLberth)
    {
        p.number=snumber;
        p.alloted=WLberth;
        passenger_data.put(p.passengerId, p);
        wlList.add(p.passengerId);
        System.out.println("------------------------");
        System.out.println("Passenger ID :" + p.passengerId);
        System.out.println("Passenger Name :" + p.name);
        System.out.println("passenger Age :" + p.age);
        System.out.println("Passenger Gender :" + p.gender);
        System.out.println("Passenger Berth :" +snumber+WLberth);
        System.out.println("YOU ARE IN WAITING LIST");
        System.out.println("-------------------------");
    }
    public void passengerDetails() {
        if (passenger_data.size() == 0) {
            System.out.println("No Passengers Details Found...");
        } else {
            for(passenger a: passenger_data.values())
            {
                System.out.println("--------------------------");
                System.out.println("Passenger ID :" + a.passengerId);
                System.out.println("Passenger Name :" + a.name);
                System.out.println("Passenger Age :" + a.age);
                System.out.println("Passenger Gender :" + a.gender);
                System.out.println("Alloted birth :" + a.number + a.alloted);
                System.out.println("--------------------------");
            }
        }
    }
     //---------------------------------------------cancel ticket---------------------------------|
    public void cancelTicket(int passengerID)
    {
        passenger p = passenger_data.get(passengerID);
        passenger_data.remove(passengerID);
        bookedTicketList.remove(passengerID);
        int psnumber = p.number;
        System.out.println("CANCELLED SUCCESSFULLY");
        System.out.println("---------------------------");
        if(p.alloted.equals("L"))
        {
            lowerBPos.add(psnumber);
            availLB++;
        }
        else if(p.alloted.equals("M"))
        {
            middleBPos.add(psnumber);
            availMB++;
        }
        else if(p.alloted.equals("U"))
        {
            upperBPos.add(psnumber);
            availUB++;
        }
        if(RACList.size() > 0)
        {
            passenger passengerFromRAC = passenger_data.get(RACList.poll());
            int pracNumber = passengerFromRAC.number;
            RACPos.add(pracNumber);
            RACList.remove(passengerFromRAC);
            availRAC++;

            if(wlList.size() > 0)
            {
                passenger passengerFromWL = passenger_data.get(wlList.poll());
                int  pwlNumber =passengerFromWL.number;
                WLPos.add(pwlNumber);
                wlList.remove(passengerFromWL);
                availWL++;
                passengerFromWL.number = RACPos.get(0);
                passengerFromWL.alloted = "RAC";
                RACPos.remove(0);
                RACList.add(passengerFromWL.passengerId);

                availWL++;
                availRAC--;
            }
            Main.bookTicket(passengerFromRAC);
        }
    }
    public void availableTickets()
    {
        System.out.println("----------------Available seats------------------");
        System.out.println("Available lower berths :" + availLB);
        System.out.println("Available middle berths :" + availMB);
        System.out.println("Available upper berths :"+ availUB);
        System.out.println("Available RACs :" + availRAC);
        System.out.println("Available waiting list :" + availWL);
        System.out.println("---------------------------------------------------");
    }


}
