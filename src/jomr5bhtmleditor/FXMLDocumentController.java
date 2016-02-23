/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jomr5bhtmleditor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Jared
 */
public class FXMLDocumentController implements Initializable
{
    private Stage mainStage;
    
    @FXML
    private HTMLEditor editor;
    
    @FXML
    private void handleOpenFileAction(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Select an HTML file!");
        File file = chooser.showOpenDialog(mainStage);
        
        if (file == null)
        {
            System.out.printf("Couldn't open the file!");
        }
        else
        {
            try
            {
               List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
               String data = "";
               
               for (String line: lines)
               {
                   data = data + line;
               }
               
               //input data in html editor
               this.editor.setHtmlText(data);
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    @FXML
    private void handleSaveFileAction(ActionEvent event)
    {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save File");
        File file = chooser.showSaveDialog(mainStage);
        
        //try and save the file
        if (file == null)
        {
            System.out.printf("Couldn't open the file");
        }
        else
        {
            try (PrintWriter out = new PrintWriter(file.getAbsolutePath()))
            {
                out.write(editor.getHtmlText());
                out.flush();
                out.close();
            } 
            catch (FileNotFoundException ex)
            {
                System.out.println(ex.getMessage());
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void setStage(Stage stage)
    {
        this.mainStage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
