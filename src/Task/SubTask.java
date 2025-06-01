package Task;

public class SubTask extends Task {

    private int epicId;

    public SubTask(String name, String description, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public String toString() {
        String tmp = super.toString();
        tmp = tmp + ", epic: " + epicId;
        return tmp;
    }

    @Override
    public void setStatus(TaskStatus status) {
        super.setStatus(status);
    }

}

