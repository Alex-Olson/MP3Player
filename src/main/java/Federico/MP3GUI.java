package Federico;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by Federico on 12/6/2016.
 */
public class MP3GUI extends JFrame  {
    private JButton openFileButton;
    private JButton playButton;
    private JButton stopButton;
    private JPanel rootPanel;
    private JList Playlist;
    private JButton QuitButton;
    private JTextField NowPlayingField;

    MP3 Song;
    DefaultListModel <MP3> PlaylistModel;

    public MP3GUI() {

        setContentPane(rootPanel);
        pack();
        Song = null;

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        PlaylistModel = new DefaultListModel<>();
        Playlist.setModel(PlaylistModel);

        openFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectFile();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Song != null) {
                    NowPlayingField.setText(Song.getFilename());
                    Song.play();
                }
            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Song != null) {
                    Song.close();
                }
            }
        });
        QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void selectFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("C:/Users/Federico/Desktop"));
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File f = chooser.getSelectedFile();
            Song = new MP3(f.getAbsolutePath());
            PlaylistModel.addElement(Song);

        }
    }
}
