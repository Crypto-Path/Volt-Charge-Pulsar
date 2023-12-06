public class NoteHandler {
  private Judgment judgments[] = new Judgment[] {
    new Judgment("Perfect", 8, 9),
    new Judgment("Good", 12, 4),
    new Judgment("Bad", 18, 1),
    new Judgment("Miss", 27, 0)
  };
  private int score = 0;
  private float accuracy = 1;

  /**
     *
   * @param ms absolute distance in time from being directly on beat with the note
   * @return 
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
        return true;
      }
    }

    return true;
  }

  public int calcScore() {
    int score = 0;

    // Get the range the note was hit in
    for (Judgment judgment : judgments) {
      score += judgment.getCount() * judgment.getValue();
    }

    return score;
  }

  public float calcAccuracy() {
    // (perfects * 1, goods * 0.5, bads * 0.25) / (totalNotes + missCount)
    int totalNotes = 0;
    int weightedAccuracy = 0;

    for (Judgment judgment : judgments) {
      totalNotes += judgment.getCount();
    }
    totalNotes += judgments[judgments.length - 1].getCount();

    for (int i = 0; i < judgments.length - 1; i++) {
      weightedAccuracy += judgments[i].getCount() * (1 / Math.pow(2, i));
    }

    return (float)weightedAccuracy / (float)totalNotes;
  }
}
