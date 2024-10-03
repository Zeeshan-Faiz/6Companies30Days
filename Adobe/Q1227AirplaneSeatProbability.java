package Adobe;

/*
n passengers board an airplane with exactly n seats. The first passenger has lost the ticket and 
picks a seat randomly. But after that, the rest of the passengers will:
    Take their own seat if it is still available, and
    Pick other seats randomly when they find their seat occupied

Return the probability that the nth person gets his own seat.

Example 1:

*/

public class Q1227AirplaneSeatProbability {

    public double nthPersonGetsNthSeat(int n) {
        if (n == 1) {
            return (double) n;// if seat is one then there is one seat and the person will seat in correct
                              // seat.
        } else {
            return 0.5;// for ex:if 10 seat are available the person will sit either the person seat or
                       // the another seat, then the probability will be 1/2.
        }
    }
}