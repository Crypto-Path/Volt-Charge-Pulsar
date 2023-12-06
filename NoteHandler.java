public class NoteHandler {
  private Judgment judgments[] = new Judgment[] {
    new Judgment("Perfect", 8, 9),
    new Judgment("Good", 12, 4),
    new Judgment("Bad", 18, 1),
    new Judgment("Miss", 27, 0)
  };
  private int score = 0;
  private float accuracy = 1;
  private int combo = 0;
  private int maxCombo = 0;

  /**
     *
   * @param ms absolute distance in time from being directly on beat with the note
   * @return boolean Whether the note was hit or not
   */
  public boolean judgeNote(float ms) {

    // If you hit too early
    if (judgments[judgments.length - 1].getTiming() < ms) {
      return false;
    }

    // Get the range the note was hit in
    for (Judgment judgment : judgments) {
      if (judgment.getTiming() >= ms) {
        judgment.increaseCount();
        combo++;

        // If not was a miss, but it was still hit
        if (judgment.equals(judgments[judgments.length - 1])) {
          combo = 0;
          return true;
        }

        // If current combo is higher than the highest combo, match highest combo with it
        if (combo > maxCombo) {
          maxCombo = combo;
        }
        return true;
      }
    }

    return true;
  }

  public int getCombo() {
    return maxCombo;
  }

  public int calcScore() {
    int score = 0;

    // Get the range the note was hit in
    for (Judgment judgment : judgments) {
      score += judgment.getCount() * judgment.getValue();
    }

    return score * maxCombo;
  }

  public float calcAccuracy() {
    // (perfects * 1, goods * 0.5, bads * 0.25) / (totalNotes + missCount)
    float totalNotes = 0;
    float weightedAccuracy = 0;

    for (Judgment judgment : judgments) {
      totalNotes += judgment.getCount();
    }
    totalNotes += judgments[judgments.length - 1].getCount();

    for (int i = 0; i < judgments.length - 1; i++) {
      weightedAccuracy += judgments[i].getCount() * (1.0 / Math.pow(2, i));
    }

    return weightedAccuracy / totalNotes;
  }

  public String getJudgmentCount(int index) {
    String text = "";
    text += judgments[index].getName() + " Count: " + judgments[index].getCount();
    return text;
  }

  public static void main(String[] args) {
    NoteHandler noteHandler = new NoteHandler();
    noteHandler.judgeNote(1);
    noteHandler.judgeNote(2);
    noteHandler.judgeNote(3);
    noteHandler.judgeNote(4);
    noteHandler.judgeNote(5);
    noteHandler.judgeNote(6);
    noteHandler.judgeNote(7);
    noteHandler.judgeNote(8);
    noteHandler.judgeNote(9);
    noteHandler.judgeNote(10);
    noteHandler.judgeNote(11);
    noteHandler.judgeNote(12);
    noteHandler.judgeNote(13);

    System.out.println("Score: " + noteHandler.calcScore());
    System.out.println("Accuracy: " + noteHandler.calcAccuracy() + "%");
    System.out.println("Combo: " + noteHandler.getCombo() + "x");
    System.out.println(noteHandler.getJudgmentCount(0));
    System.out.println(noteHandler.getJudgmentCount(1));
    System.out.println(noteHandler.getJudgmentCount(2));
    System.out.println(noteHandler.getJudgmentCount(3));
  }
}
