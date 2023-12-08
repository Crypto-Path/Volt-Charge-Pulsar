import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import Gameplay.NoteRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class RendererAndMusic {

  static JFrame frame = new JFrame("Volt Charge Pulsar");

  public static void main(String[] args) {
    // Needed for user input
    JPanel panel = new JPanel();
    frame.add(panel);

    System.out.println("Initiating Frame");
    JFrame frame = new JFrame("Note renderer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
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
    audioPlayer.setBounds(audioPlayer.getX(), audioPlayer.getY(), audioPlayer.getWidth(), audioPlayer.getHeight());
    
    System.out.println("Initiating Renderer");
    NoteRenderer noteRenderer = new NoteRenderer(frame, new int[][][] {
        /* Lane 1 Up */ { { 0, 5 }, { 16, 5 } },
        /* Lane 2 Down */ { { 19, 2} },
        /* Lane 3 Left */ { { 8, 9 }, { 18, 2} },
        /* Lane 4 Right */ { { 4, 5 }, { 17, 2} }
    });
    frame.add(noteRenderer);
    noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    System.out.println("Finalizing Renderer");
    
    frame.setLayout(null);
    frame.setVisible(true);

    // Emulates audio progress playing
    Timer timer = new Timer(20, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
          noteRenderer.setSongPosition((float) (audioPlayer.getProgress() / (60000000 / (96.5 * 2)) + 1));
          frame.repaint();
      }
  }); 
  timer.start();
  }
}
