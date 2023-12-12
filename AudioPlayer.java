import java.io.File;
import java.io.IOException;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer extends JButton {

  private Clip clip;

  private boolean isShown;

  public Components.Button playButton;

  private static JFrame frame;

  private int x;
  private int y;
  private int w;
  private int h;

  private boolean audioPlaying = false;

  public AudioPlayer(JFrame frame, int w, int h, boolean isShown) {
    System.out.println("Initiating Audio: Display data");
    this.x = frame.getWidth() - (w + 20);
    this.y = frame.getHeight() - (h + 45);
    this.w = w;
    this.h = h;
    this.isShown = isShown;

    setContentAreaFilled(false);
    setBorderPainted(false);
    if (isShown) {
      System.out.println("Initiating Audio: Play button");
      playButton = new Components.Button("   ▶︎");
      playButton.setRadius(this.h - 20);
      frame.add(playButton);
      playButton.setLocation(x + 10, y + 10);
      playButton.setSize(this.h - 20, this.h - 20);

      System.out.println("Initiating Audio: Button listener");
      playButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
          if (audioPlaying) {
            playButton.setText("   ▶︎");
            audioPlaying = false;
            pause();
            return;
          }
          playButton.setText("❚❚");
          audioPlaying = true;
          play();
        }
      });
    }
  }

  public void setAudio(String filePath) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
    System.out.println("Audio: Set audio file");
    File audioFile = new File(filePath);
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

    clip = AudioSystem.getClip();
    clip.open(audioInputStream);
    // clip.setLoopPoints(PreviewPoint, -1);
  }

  // Timer timer = new Timer(33, new ActionListener() {
  //   @Override
  //   public void actionPerformed(ActionEvent e) {
  //     repaint();
  //     if (clip.getMicrosecondPosition() == clip.getMicrosecondLength()) {
  //       stop();
  //     }
  //   }
  // });

  public void play() {
    System.out.println("Audio: Playing audio");
    clip.start();
    // timer.start();
  }

  public void pause() {
    System.out.println("Audio: Pausing audio");
    clip.stop();
    // timer.stop();
  }

  public void stop() {
    System.out.println("Audio: Ending audio");
    if (clip != null) {
      clip.stop();
      clip.close();
      // timer.stop();
    }
  }

  public float getProgress() {
    return clip.getMicrosecondPosition();
  }

  public float getLength() {
    return clip.getMicrosecondLength();
  }

  public boolean isPlaying() {
    return clip != null && clip.isActive();
  }

  // Draw player
  /*
   * Play/Pause Button
   * Draggable progress bar
   * Progress in seconds and total time of song
   */

  @Override
  protected void paintComponent(Graphics g) {
    if (isShown) {
      int s = (int) (clip.getMicrosecondPosition() / (1000000) % 60);
      int sL = (int) (clip.getMicrosecondLength() / (1000000) % 60);
      String timeText = (clip.getMicrosecondPosition() / (1000000 * 60) + ":" + ((s < 10) ? "0" : "") + s + "/"
          + (clip.getMicrosecondLength() / (1000000 * 60) + ":" + ((sL < 10) ? "0" : "") + sL));
      FontMetrics metrics = g.getFontMetrics();
      int textWidth = metrics.stringWidth(timeText);
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(new Color(127, 127, 127));
      g2.fillRoundRect(0, 0, getWidth(), getHeight(), this.h, this.h);
      
      // Play button
      playButton.setLocation(x + 10, y + 10);
      playButton.setSize(this.h - 20, this.h - 20);

      // Progress bar
      int barHeight = 8;
      g2.setColor(new Color(100, 100, 120));
      g2.fillRoundRect(this.h, this.h / 2 - barHeight / 2, (int) (w - 2 * this.h), barHeight, barHeight, barHeight);

      g2.setColor(new Color(75, 100, 160));
      g2.fillRoundRect(this.h, this.h / 2 - barHeight / 2,
          (int) (clip.getMicrosecondPosition() * 1.0 / clip.getMicrosecondLength() * (w - 2 * this.h)), barHeight,
          barHeight, barHeight);

      // Progress timer
      g2.drawString(timeText, this.w - this.h / 2 - metrics.stringWidth(timeText) / 2,
          this.h / 2 + metrics.getHeight() / 4);

      // System.out.println(clip.getMicrosecondPosition() * 1.0 / clip.getMicrosecondLength());

      super.paintComponent(g);
    }
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  public int getW() {
    return this.w;
  }

  public int getH() {
    return this.h;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        System.out.println("Initiating Frame");
        JFrame frame = new JFrame("AudioPlayer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Finalizing Frame");

        System.out.println("Initiating Audio Player");
        AudioPlayer audioPlayer = new AudioPlayer(frame, 300, 50, true);
        try {
          audioPlayer.setAudio("audio.wav");
        } catch (IOException e) {
          System.out.println("Error - AudioPlayer.java: General error. lol code bad.");
          e.printStackTrace();
        } catch (LineUnavailableException e) {
          System.out.println("Error - AudioPlayer.java: Line Unavailable");
          e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
          System.out.println("Error - AudioPlayer.java: Audio file extension unavailable; Extension supported: .wav");
          e.printStackTrace();
        }
        frame.add(audioPlayer);
        frame.setLayout(null);
        audioPlayer.setBounds(audioPlayer.x, audioPlayer.y, audioPlayer.w, audioPlayer.h);
        
        Timer timer = new Timer(33, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          frame.repaint();
          audioPlayer.setX(frame.getWidth() - (audioPlayer.getW() + 20));
          audioPlayer.setY(frame.getHeight() - (audioPlayer.getH() + 45));
          }
        });
        timer.start();

      }
    });
  }
}
