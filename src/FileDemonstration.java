import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileDemonstration extends JFrame {
    private JTextArea outputArea;// Used for output
    private JScrollPane scrollPane; //Used to provide scrolling to ouput

    //setup GUI
    public FileDemonstration(){
        super("Testing class File");

        outputArea = new JTextArea();

        //add outputArea to scrollpane
        scrollPane  = new JScrollPane(outputArea);

        add(scrollPane, BorderLayout.CENTER);// add scrollPane to GUI

        setSize(400,400);//set GUI size
        setVisible(true);//display

        analyzePath();//create and analyze File Object
    }//end of FileDemonstration constructor

    //allow user to specify file or directory name
    private File getFileOrDirectory(){
        //display file dialog, so user can choose file or directory to open
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        //if user clicked Cancel button on dialog, return
        if (result == JFileChooser.CANCEL_OPTION){
            System.exit(1);
        }

        File fileName = fileChooser.getSelectedFile();//get File

        //display error if invalid
        if ((fileName == null)||(fileName.getName().equals(""))){
            JOptionPane.showMessageDialog(this,"Invalid Name", "Invalid Name",JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        return fileName;
    }//end method getFile


    //display information about file or directory user specifies
    public void analyzePath()
        {
        //create file object based on user input
        File name = getFileOrDirectory();

        if (name.exists())//if name exists, output information about it
        {
            //display file(or directory) information about it
            outputArea.setText(String.format("%s%s\n%s\n%s\n%s\n%s%s\n%s%s\n%s%s\n%s%s\n%s%s",name.getName(),"exists",
                    (name.isFile() ? "is a file" : "is not a file"),(name.isDirectory() ? "is a directory": "is not a directory"),
                    (name.isAbsolute()? "is absolute path": "is not absolute path"),"Last Modified: ", name.lastModified(),
                    "length : ", name.length(), "Path : ",name.getPath(),"Absolute Path : ",name.getAbsolutePath(), "Parent : ", name.getParent()));

            if (name.isDirectory())//output directory listing
            {
                String[] directory = name.list();
                outputArea.append( "\n\n Directory contents : \n" );

                for (String directoryName :directory)
                    outputArea.append(directoryName + "\n");
                //end elseif
            }
        }//end outer if

        //neither a file nor a directory, output error message
        else {
            JOptionPane.showMessageDialog(this, name + "does not exist.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }//end else

    }//end method anaylzePath
}//end class File Demonstration
