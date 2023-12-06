import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Components.Button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class AudioPlayer extends JButton {

    private Clip clip;
    private URL url;
    private AudioInputStream audioIn;

    private boolean isShown;

    private Components.Button playButton;

    private JFrame frame;

    private int x;
    private int y;
    private int w;
    private int h;

    private boolean audioPlaying = false;

   public AudioPlayer(JFrame frame, int w, int h, boolean isShown) {
      this.x = frame.getWidth() - (w + 20);
      this.y = frame.getHeight() - (h + 45);
      this.w = w;
      this.h = h;
      this.isShown = isShown;

      setContentAreaFilled(false);
      setBorderPainted(false);
      if (isShown) {
        playButton = new Components.Button("   ▶︎");
        playButton.setRadius(this.h - 20);
        frame.add(playButton);
        playButton.setLocation(x + 10, y + 10);
        playButton.setSize(this.h - 20, this.h - 20);

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

   public void setAudio(String path) {
      try {
        url = this.getClass().getClassLoader().getResource("audio.wav");
        audioIn = AudioSystem.getAudioInputStream(url);
        clip.open(audioIn);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public void play() {
      clip.start();
   }

   public void pause() {
      clip.stop();
   }

   public float getProgress() {
    return clip.getMicrosecondPosition();
   }

   public float getLength() {
    return clip.getMicrosecondLength();
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
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(127, 127, 127));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), this.h, this.h);

        super.paintComponent(g);
      }
    }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Cool Button");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                frame.setSize(400, 200);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                AudioPlayer audioPlayer = new AudioPlayer(frame, 300, 50, true);
                audioPlayer.setAudio("audio.wav");
                frame.add(audioPlayer);
                frame.setLayout(null);
                audioPlayer.setBounds(audioPlayer.x, audioPlayer.y, audioPlayer.w, audioPlayer.h);
            }
        });
   }
}
