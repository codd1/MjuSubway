package mju.com.station;

public class StationInfo {
    private String startName;
    private String destName;
    private int time;
    private int dist;
    private int price;

    public StationInfo(String startName, String destName, int time, int dist, int price) {
        this.startName = startName;
        this.destName = destName;
        this.time = time;
        this.dist = dist;
        this.price = price;
    }

    public String getStartName() {
        return this.startName;
    }

    public String getDestName(){ return this.destName; }

    public int getTime(){ return this.time; }

    public int getDist(){ return this.dist; }

    public int getPrice(){ return this.price; }

    public boolean equals(Object obj){
        if(this.startName == ((StationInfo)obj).startName){
            return true;
        } else{
            return false;
        }
    }
}