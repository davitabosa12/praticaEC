package br.ufc.smd.activitydiarynormal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ActivityLog {
    @PrimaryKey(autoGenerate = true)
    int uid;

    @ColumnInfo(name = "activity_type")
    public int activityType;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "activity_status")
    public int activityStatus;

    public ActivityLog(int activityType, long timestamp, int activityStatus) {
        if(activityType < 2 || activityType > 7)
        {
            throw  new RuntimeException("Invalid activityType: " + activityType + ". Accepted values: ActivityLog.STILL, ActivityLog.RUNNING, ActivityLog.ON_BICYCLE," +
                    "ActivityLog.STILL_HEADPHONE, ActivityLog.RUNNING_HEADPHONE, ActivityLog.ON_BICYCLE_HEADPHONE");
        }
        if(activityStatus < 0 || activityStatus > 1)
        {
            throw  new RuntimeException("Invalid activityStatus: " + activityStatus+ "Accepted values: ActivityLog.STARTING, ActivityLog.STOPPING");
        }
        this.activityType = activityType;
        this.timestamp = timestamp;
        this.activityStatus = activityStatus;
    }


    public final static int STARTING = 0, STOPPING = 1;


    public final static int STILL = 2, RUNNING = 3, ON_BICYCLE = 4,
        STILL_HEADPHONE = 5, RUNNING_HEADPHONE = 6, ON_BICYCLE_HEADPHONE = 7;


    @Override
    public String toString() {
        return "ActivityLog{" +
                "uid=" + uid +
                ", activityType=" + activityType +
                ", timestamp=" + timestamp +
                ", activityStatus=" + activityStatus +
                '}';
    }
}
