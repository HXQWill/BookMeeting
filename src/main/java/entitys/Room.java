package entitys;

/**
 * Created by car on 2016/8/22.
 */
public class Room {
    private String name;
    private String owner;
    private String time;
    private String content;
    private String local;
    private int id;
    private int imageId;

    public Room() {
    }

    public Room(int imageId, String owner, String time, String content) {
        this.imageId = imageId;
        this.owner = owner;
        this.time = time;
        this.content = content;
    }

    public Room(String name, String local) {
        this.name = name;
        this.local = local;
    }

    public Room(int id, int imageId, String local, String content, String time, String owner, String name) {
        this.id = id;
        this.local = local;
        this.content = content;
        this.time = time;
        this.owner = owner;
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        /*
        return "Room{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                ", local='" + local + '\'' +
                ", id=" + id +
                ", imageId=" + imageId +
                '}';
        */
        return name + local;
    }
}
