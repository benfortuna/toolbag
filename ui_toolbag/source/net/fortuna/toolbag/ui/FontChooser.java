package net.fortuna.toolbag.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.fortuna.toolbag.ui.util.WindowUtils;

/**
 * @author benf
 *
 * A pane used for selecting a font. 
 */
public class FontChooser extends JPanel implements ListSelectionListener {

    private static final Font DEFAULT_FONT = new Font("SansSerif", Font.PLAIN,
            12);

    private static final Integer[] DEFAULT_SIZES = { new Integer(8),
            new Integer(10), new Integer(12), new Integer(16), new Integer(18),
            new Integer(24)};

    private Font selectedFont;
    
    private JTextField fontFamilyTF;
    
    private JList fontFamilyList;
    
    private JTextField fontStyleTF;
    
    private JList fontStyleList;
    
    private JTextField fontSizeTF;
    
    private JList fontSizeList;

    private JLabel previewLabel;

    // re-use a static dialog and chooser..
    private static JDialog dialog;

    private static FontChooser chooser;

    public FontChooser() {
        this(DEFAULT_FONT);
    }

    /**
     * Constructor for FontChooser.
     */
    public FontChooser(Font font) {
        super(new BorderLayout());

        setSelectedFont(font);

        previewLabel = new JLabel(getSelectedFont().getFontName(), JLabel.CENTER);
        previewLabel.setFont(getSelectedFont());
        previewLabel.setBorder(BorderFactory.createTitledBorder("Preview"));
        
        fontFamilyTF = new JTextField();
        fontFamilyTF.setEditable(false);
        
        fontFamilyList = new JList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        fontFamilyList.setSelectedValue(getSelectedFont().getName(), true);
        fontFamilyList.getSelectionModel().addListSelectionListener(this);
        
        JPanel fontFamilyPanel = new JPanel(new BorderLayout());
        fontFamilyPanel.add(fontFamilyTF, BorderLayout.NORTH);
        fontFamilyPanel.add(new JScrollPane(fontFamilyList), BorderLayout.CENTER);
        
        fontStyleTF = new JTextField();
        fontStyleTF.setEditable(false);
        
        fontStyleList = new JList(new Object[] {"Plain", "Bold", "Italic", "Bold Italic"});
        fontStyleList.setSelectedIndex(getSelectedFont().getStyle());
        fontStyleList.getSelectionModel().addListSelectionListener(this);
        
        JPanel fontStylePanel = new JPanel(new BorderLayout());
        fontStylePanel.add(fontStyleTF, BorderLayout.NORTH);
        fontStylePanel.add(new JScrollPane(fontStyleList), BorderLayout.CENTER);
        
        fontSizeTF = new JTextField(2);
        fontSizeTF.addActionListener(new ActionListener() {
            /* (non-Javadoc)
             * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
             */
            public final void actionPerformed(final ActionEvent e) {
                fontSizeList.setSelectedIndices(new int[] {});
                updateSelection();
            }
        });
        
        fontSizeList = new JList(new Integer[] {new Integer(10), new Integer(12), new Integer(14), new Integer(18), new Integer(24)});
        fontSizeList.getSelectionModel().addListSelectionListener(this);
        fontSizeList.setSelectedValue(new Integer(getSelectedFont().getSize()), true);
        
        JPanel fontSizePanel = new JPanel(new BorderLayout());
        fontSizePanel.add(fontSizeTF, BorderLayout.NORTH);
        fontSizePanel.add(new JScrollPane(fontSizeList), BorderLayout.CENTER);

        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
//        selectionPanel.setBorder(BorderFactory
//                .createTitledBorder("Font Selection"));
        selectionPanel.add(fontFamilyPanel);
        selectionPanel.add(fontStylePanel);
        selectionPanel.add(fontSizePanel);

        JPanel previewPanel = new JPanel(new BorderLayout());
        previewPanel.setBorder(BorderFactory.createTitledBorder("Preview"));
//        previewPanel.add(previewLabel);

        add(selectionPanel, BorderLayout.NORTH);
        add(previewLabel, BorderLayout.CENTER);
        
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    /**
     * Returns the selectedFont.
     * 
     * @return Font
     */
    public Font getSelectedFont() {
        return selectedFont;
    }

    /**
     * Sets the selectedFont.
     * 
     * @param selectedFont
     *            The selectedFont to set
     */
    public void setSelectedFont(Font selectedFont) {
        this.selectedFont = selectedFont;
    }

    /* (non-Javadoc)
     * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
     */
    public void valueChanged(ListSelectionEvent e) {
        updateSelection();
    }
    
    private void updateSelection() {
        fontFamilyTF.setText(fontFamilyList.getSelectedValue().toString());
        fontStyleTF.setText(fontStyleList.getSelectedValue().toString());
        if (fontSizeList.getSelectedIndex() >= 0) {
            fontSizeTF.setText(fontSizeList.getSelectedValue().toString());
        }
        setSelectedFont(new Font(fontFamilyList.getSelectedValue().toString(),
                fontStyleList.getSelectedIndex(), Integer.parseInt(fontSizeTF.getText())));
        previewLabel.setText(getSelectedFont().getFontName());
        previewLabel.setFont(getSelectedFont());
//        previewLabel.revalidate();
    }

    public static Font showDialog(Component component, String title,
            Font initialFont) {
        if (dialog == null || dialog.getParent() != component) {
            Window root = SwingUtilities.getWindowAncestor(component);

            if (root instanceof Frame) {
                dialog = new JDialog((Frame) root, title, true);
            }
            else {
                dialog = new JDialog((Frame) null, title, true);
            }

            if (initialFont != null) {
                chooser = new FontChooser(initialFont);
            }
            else {
                chooser = new FontChooser();
            }

            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    dialog.setVisible(false);
                }
            });

            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    chooser.setSelectedFont(null);
                    dialog.setVisible(false);
                }
            });

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            dialog.getContentPane().add(chooser, BorderLayout.CENTER);
            dialog.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        }

        dialog.setTitle(title);
        chooser.setSelectedFont(initialFont);

        dialog.pack();
        WindowUtils.center(dialog);
        dialog.setVisible(true);

        return chooser.getSelectedFont();
    }

    public static void main(String[] args) {
        /*
         * JFrame f = new JFrame("Font Chooser Test");
         * f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         * f.getContentPane().add(new FontChooser()); f.pack();
         * f.setVisible(true);
         */

        System.out.println(showDialog(new JFrame(), "Choose Font", new Font(
                "Arial Black", Font.ITALIC, 24)));

        System.exit(0);
    }
}