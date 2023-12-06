package Gameplay;
public class Judgment {
    private String name;
    private int timing;
    private int value;
    private int count = 0;

    public Judgment(String name, int timing, int value) {
      this.name = name;
      this.timing = timing;
      this.value = value;
    }

    public void increaseCount() {
      this.count++;
    }

    /**
     * @return String return the name of the judgement
     */
    public String getName() {
        return name;
    }

    /**
     * @return int return the timing range for the judgement
     */
    public int getTiming() {
        return timing;
    }

    /**
     * @return int return the timing range for the judgement
     */
    public int getValue() {
        return value;
    }

    /**
     * @return int return the number of times the judgement has been met
     */
    public int getCount() {
        return count;
    }

}
