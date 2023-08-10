import java.util.*;
public class Main {
    public static void bookTicket(passenger p)
    {
        ticketBook tb = new ticketBook();
        if(ticketBook.availWL == 0)
        {
            System.out.println("NO TICKETS AVAILABLE");
            System.out.println("----------------------");
        }
        //------------------------------------BERTHS--------------------------------------------------|
        if((p.bp.equals("L") && ticketBook.availLB>0) || (p.bp.equals("M") && ticketBook.availMB>0) || (p.bp.equals("U") && ticketBook.availUB>0)) {
            if (p.bp.equals("L")) {
                System.out.println("Lower Berth Given");
                tb.bookTicket(p, (ticketBook.lowerBPos.get(0)), "L");
                ticketBook.lowerBPos.remove(0);
                ticketBook.availLB--;
            } else if (p.bp.equals("M")) {
                System.out.println("Middle Berth Given");
                tb.bookTicket(p, (ticketBook.middleBPos.get(0)), "M");
                ticketBook.middleBPos.remove(0);
                ticketBook.availMB--;
            } else if (p.bp.equals("U")) {
                System.out.println("Upper Berth Given");
                tb.bookTicket(p, (ticketBook.upperBPos.get(0)), "U");
                ticketBook.upperBPos.remove(0);
                ticketBook.availUB--;
            }
        }
        //--------------------------------AVAILABLE BERTHS------------------------------------------|
        else if(ticketBook.availLB > 0)
        {
            System.out.println("Lower Berth Given");
            tb.bookTicket(p,(ticketBook.lowerBPos.get(0)),"L");
            ticketBook.lowerBPos.remove(0);
            ticketBook.availLB--;
        }
        else if(ticketBook.availMB > 0)
        {
            System.out.println("Middle Berth Given");
            tb.bookTicket(p,(ticketBook.middleBPos.get(0)),"M");
            ticketBook.middleBPos.remove(0);
            ticketBook.availMB--;
        }
        else if(ticketBook.availUB > 0)
        {
            System.out.println("Upper Berth Given");
            tb.bookTicket(p,(ticketBook.upperBPos.get(0)),"U");
            ticketBook.upperBPos.remove(0);
            ticketBook.availUB--;
        }
        else if(ticketBook.availRAC > 0)
        {
            System.out.println("RAC Given");
            tb.racTicket(p,(ticketBook.RACPos.get(0)),"RAC");
            ticketBook.RACPos.remove(0);
            ticketBook.availRAC--;
        }
        else if(ticketBook.availWL > 0)
        {
            System.out.println(" WAITING LIST ");
            tb.wlTicket(p,(ticketBook.WLPos.get(0)),"WL");
            ticketBook.WLPos.remove(0);
            ticketBook.availWL--;
        }

        //------------------------------------cancellation--------------------------------------|


    }
    public static void cancelTicket(int id) {
        ticketBook tb = new ticketBook();
        if (!tb.passenger_data.containsKey(id)) {
            System.out.println("Passenger details not found");
        }
        else
        {
            tb.cancelTicket(id);
        }
    }

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        boolean loop = true;
        while(loop)
        {
            System.out.println("WELCOME TO JAYVEER RAILWAYS");
            System.out.println("----------------------------");
            System.out.println("1.book ");
            System.out.println("2.Cancellation");
            System.out.println("3.Available Tickets ");
            System.out.println("4.Booked Tickets ");
            System.out.println("5.exit ");
            System.out.println("----------------------------");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter the passenger name :");
                    String name = sc.next();
                    System.out.println("Enter the passenger age :");
                    int age=sc.nextInt();
                    System.out.println("Enter the passenger gender [m,f] :");
                    String gender = sc.next();
                    System.out.println("enter the passenger birth place [L,U,M] :");
                    String bp = sc.next();
                    passenger p = new passenger(name,age,gender,bp);
                    bookTicket(p);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the passenger ID :");
                    int id = sc.nextInt();
                    cancelTicket(id);
                    break;
                }
                case 3:
                {
                    ticketBook tb = new ticketBook();
                    tb.availableTickets();
                    break;
                }
                case 4:
                {
                    ticketBook tb = new ticketBook();
                    tb.passengerDetails();
                    break;
                }
                case 5:
                {
                    loop=false;
                    System.out.println("THANK YOU ");
                    break;
                }
            }
        }
    }
}
