package mju.com.station;

public class StationAmenities {
    private String currentName;
    private String previousName;
    private String nextName;
    private int line;
    private int stairs;
    private String vendingMachine;
    private String airConditioner;
    private String toilet;
    private String getOffQuick;
    private String congestion;


    public StationAmenities(String currentName, String previousName, String nextName, int line, int stairs, String vendingMachine,
                            String airConditioner, String toilet, String getOffQuick, String congestion) {
        this.currentName = currentName;
        this.previousName = previousName;
        this.nextName = nextName;
        this.line = line;
        this.stairs = stairs;
        this.vendingMachine = vendingMachine;
        this.airConditioner = airConditioner;
        this.toilet = toilet;
        this.getOffQuick = getOffQuick;
        this.congestion = congestion;
    }

    public String getCurrentName() {
        return this.currentName;
    }

    public String getPreviousName(){ return this.previousName; }

    public String getNextName(){ return this.nextName; }

    public int getLine(){ return this.line; }

    public int getStairs(){ return this.stairs; }

    public String getVendingMachine(){ return this.vendingMachine; }

    public String getAirConditioner(){ return this.airConditioner; }

    public String getToilet(){ return this.toilet; }

    public String getGetOffQuick(){ return this.getOffQuick; }

    public String getCongestion(){ return this.congestion; }


    public boolean equals(Object obj){
        if(this.currentName == ((StationAmenities)obj).currentName){
            return true;
        } else{
            return false;
        }
    }
}
