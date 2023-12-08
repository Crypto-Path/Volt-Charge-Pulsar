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

public class Game {
  private JFrame frame;

  private AudioPlayer audioPlayer;
  private NoteRenderer noteRenderer;
  private Timer timer;

  public Game(JFrame frame, int level, int difficulty) {
    this.frame = frame;
  }

  public void InitMenu() {
    System.out.println("Initiating Frame");
    JFrame frame = new JFrame("Note renderer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setLayout(null);
    frame.getContentPane().setBackground(Color.WHITE);
    System.out.println("Finalizing Frame");
  }

  public void initGUI() {
    System.out.println("Initiating Audio Player");
    audioPlayer = new AudioPlayer(frame, 300, 50, true);
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
    audioPlayer.setBounds(audioPlayer.getX(), audioPlayer.getY(), audioPlayer.getW(), audioPlayer.getH());
    
    System.out.println("Initiating Renderer");
    noteRenderer = new NoteRenderer(frame, new int[][][] {
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
    timer = new Timer(20, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          noteRenderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
          noteRenderer.setSongPosition((float) (audioPlayer.getProgress() / (60 * 1000000 / (96.5 * 2)) + 1));
          
          audioPlayer.setX(frame.getWidth() - (audioPlayer.getW() + 20));
          audioPlayer.setY(frame.getHeight() - (audioPlayer.getH() + 45));
          frame.repaint();
      }
    }); 
    timer.start();
  }

  public void unload() {
    timer.stop();
    timer = null;
    frame.remove(audioPlayer.playButton);
    audioPlayer.playButton = null;
    frame.remove(audioPlayer);
    audioPlayer = null;

    frame.remove(noteRenderer);
    noteRenderer = null;
  }
}
