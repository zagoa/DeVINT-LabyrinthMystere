package dvt.labyrinth.menu;

import dvt.labyrinth.tools.ConstantesLabyrinth;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

import static dvt.labyrinth.tools.ConstantesLabyrinth.*;

/**
 * Menu inspiré du menu principal.
 * @author Justal Kevin
 */
public class SelectSize extends MenuGeneric {
    private static final long serialVersionUID = 1L;

    /**
     * Le constructeur du menu
     * Permet de construire un Menu avec tout les composants
     */
    public SelectSize() {
        super();

        addLabel(TITLE_GAME_SIZE);

        addMenu(SIZE.NORMAL.toString(),new ActionMenu(this,1));
        addMenu(SIZE.GRAND.toString(), new ActionMenu(this,2));
        addMenu(SIZE.TRES_GRAND.toString(), new ActionMenu(this, 3));

        addControl("DOWN", new DownAction(this));
        addControl("UP", new UpAction(this));
    }

    /**
     * La loop du menu
     */
    public void loop() {
        long lastLoopTime,timeLoop;

        while (this.isDisplayable()) {
            long now = System.nanoTime();
            lastLoopTime = now;
            render();
            if (gameChoice==5)
                this.dispose();
            else if (gameChoice != 0) {
                this.setVisible(false);
                this.getSIVOX().stop();

                switch (gameChoice) {
                    case 1: // Easy
                        selectedSize(SIZE.NORMAL);
                        return;

                    case 2: // Medium
                        selectedSize(SIZE.GRAND);
                        return;

                    case 3: // Hard
                        selectedSize(SIZE.TRES_GRAND);
                        return;

                    default:
                        break;
                }
                this.getSIVOX().stop();
                this.setVisible(true);
            }

            gameChoice = 0;

            try {
                timeLoop = (lastLoopTime - System.nanoTime() + 1000000000L/60) / 1000000;
                if(timeLoop>0) {
                    Thread.sleep(timeLoop);
                }
            } catch (InterruptedException e) {
                throw new IllegalArgumentException("");
            }
        }
    }

    public void selectedSize(SIZE s) {
        // Set config
        ConstantesLabyrinth.setConfig(s);

        // Keep config in memory
        writeConfiguration(s);

        // Go back to menu
        dispose();
    }

    public void writeConfiguration(SIZE s) {
        File inputFile = new File(CONFIG_FILE);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Element element = doc.getDocumentElement();
            element.setTextContent(s.name());

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");
            trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            OutputStream out = new FileOutputStream(CONFIG_FILE);
            trans.transform(new DOMSource(doc), new StreamResult(out));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
