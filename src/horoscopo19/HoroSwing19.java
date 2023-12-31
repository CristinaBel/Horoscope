package horoscopo19;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class HoroSwing19 extends JFrame{
    
    GridBagConstraints gbc = new GridBagConstraints();
    GridBagConstraints gbc2 = new GridBagConstraints();
    GridBagConstraints gbc3 = new GridBagConstraints();
    JPanel panel1, panel2, panel3;
    JLabel saludo, nombre, fecha, foto;
    JTextArea mensaje, prediccion;
    JTextField nombreUsuario, fechaNacimiento;
    JButton enviar;
    Pattern pattern;
    Matcher matcher;
    final String FECHA = "([0-9]{2}-[0-9]{2}-[0-9]{4})";
    BufferedImage img;
    String signo, eleccion, resultado;
    String asuntos [] = {"amor", "salud", "trabajo", "todo"};
    JComboBox asunto;
    
    public HoroSwing19(){
        super("Horóscopo de Esperanza Gracia");
        
        getContentPane().setBackground(Color.decode("#F7DBFF"));
        
        panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setSize(800, 200);
        gbc.insets = new Insets(5, 5, 5, 5); 



        saludo = new JLabel("Introduce tu nombre y tu fecha de nacimiento y dale al botón para conocer tu futuro");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        panel1.add(saludo, gbc);
        
        nombre = new JLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        panel1.add(nombre, gbc);
         
        nombreUsuario = new JTextField(20); 
        nombreUsuario.setToolTipText("Introduce tu nombre");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        panel1.add(nombreUsuario, gbc);
        
        fecha = new JLabel("Fecha de Nacimiento: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel1.add(fecha, gbc);
        
        
        fechaNacimiento = new JTextField(20);
        fechaNacimiento.setToolTipText("Introduce tu fecha de nacimiento (dd-mm-aaaa)");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill= GridBagConstraints.HORIZONTAL;
        panel1.add(fechaNacimiento, gbc);
        
        enviar = new JButton("Enviar");
        enviar.setToolTipText("Pulsa aquí para enviar tus datos zodiacales");
        enviar.setMnemonic('e');
        gbc.gridx = 3;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.fill= GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(enviar, gbc);
        
        
        enviar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {    
                    enviarActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(HoroSwing19.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        enviar.setBackground(new Color(128,0,128));
        enviar.setForeground(Color.WHITE);
        
        panel1.setBackground(Color.decode("#F7DBFF"));
        
        panel2 = new JPanel(new GridBagLayout());
        panel2.setBackground(Color.decode("#F7DBFF"));
        panel2.setBounds(0,200,800, 200);

        panel3 = new JPanel(new GridBagLayout());
        panel3.setBackground(Color.decode("#F7DBFF"));
        panel3.setBounds(0,400,800, 200);
       
        setLocation(300, 150);
        setSize(800, 600);
        setVisible(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 0;
        gbc4.gridy = 0;
        getContentPane().add(panel1, gbc4);
        gbc4.gridx = 0;
        gbc4.gridy = 1;
        getContentPane().add(panel2, gbc4);
        gbc4.gridx = 0;
        gbc4.gridy = 2;
        getContentPane().add(panel3, gbc4);
        
        WindowListener wl = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0); 
            }
        };
        addWindowListener(wl);
    }
    
                
    public void comprobarNombre(){
        if(nombreUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(this,"Introduce un nombre","Atención", JOptionPane.INFORMATION_MESSAGE);
            nombreUsuario.requestFocus();
        }
    }
    
    //Método para comprobar que la fecha sigue el patrón que queremos
    public void comprobarFecha(){
        pattern = Pattern.compile(FECHA);  
        matcher = pattern.matcher(fechaNacimiento.getText());
        if(!matcher.find()){
            JOptionPane.showMessageDialog(this,"Introduce una fecha válida (dd-mm-aaaa)","Atención", JOptionPane.INFORMATION_MESSAGE);
            fechaNacimiento.requestFocus();
            
        }
    }
                
        
     public boolean comprobarSigno() throws IOException{       
        String fecha = fechaNacimiento.getText();  
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.gridwidth = 1;
        gbc2.fill= GridBagConstraints.HORIZONTAL;
        if(!fecha.equals("")){
            String s [] = fecha.split("-");
            switch(s[1]){
                case "01":
                    if (Integer.parseInt(s[0])<= 20){
                        signo = "Capricornio"; 
                        img = ImageIO.read(new File("./resources/9081714_zodiac_capricorn_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else { 
                        signo = "Acuario";
                        img = ImageIO.read(new File("./resources/9081717_zodiac_aquarius_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "02":
                    if (Integer.parseInt(s[0])<= 18){
                        signo = "Acuario"; 
                        img = ImageIO.read(new File("./resources/9081717_zodiac_aquarius_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Piscis";
                        img = ImageIO.read(new File("./resources/9081712_zodiac_pisces_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "03":  
                    if (Integer.parseInt(s[0])<= 19){
                        signo = "Piscis"; 
                        img = ImageIO.read(new File("./resources/9081712_zodiac_pisces_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Aries";
                        img = ImageIO.read(new File("./resources/9081713_zodiac_aries_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "04":
                    if (Integer.parseInt(s[0])<= 21){
                        signo = "Aries"; 
                        img = ImageIO.read(new File("./resources/9081713_zodiac_aries_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Tauro";
                        img = ImageIO.read(new File("./resources/9081707_zodiac_taurus_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "05":
                    if (Integer.parseInt(s[0])<= 20){
                        signo = "Tauro"; 
                        img = ImageIO.read(new File("./resources/9081707_zodiac_taurus_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Géminis";
                        img = ImageIO.read(new File("./resources/9081711_zodiac_gemini_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "06":
                    if (Integer.parseInt(s[0])<= 21){
                        signo = "Géminis"; 
                        img = ImageIO.read(new File("./resources/9081711_zodiac_gemini_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Cáncer";
                        img = ImageIO.read(new File("./resources/9081710_zodiac_cancer_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "07":
                    if (Integer.parseInt(s[0])<= 21){
                        signo = "Cáncer"; 
                        img = ImageIO.read(new File("./resources/9081710_zodiac_cancer_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Leo";
                        img = ImageIO.read(new File("./resources/9081701_zodiac_leo_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "08":
                    if (Integer.parseInt(s[0])<= 22){
                        signo = "Leo"; 
                        img = ImageIO.read(new File("./resources/9081701_zodiac_leo_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Virgo";
                        img = ImageIO.read(new File("./resources/9081716_zodiac_virgo_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "09":
                    if (Integer.parseInt(s[0])<= 22){
                        signo = "Virgo"; 
                        img = ImageIO.read(new File("./resources/9081716_zodiac_virgo_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Libra";
                        img = ImageIO.read(new File("./resources/9081715_zodiac_libra_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "10":
                    if (Integer.parseInt(s[0])<= 22){
                        signo = "Libra"; 
                        img = ImageIO.read(new File("./resources/9081715_zodiac_libra_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                       panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Escorpio";
                        img = ImageIO.read(new File("./resources/9081706_zodiac_scorpio_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "11":
                    if (Integer.parseInt(s[0])<= 21){
                        signo = "Escorpio"; 
                        img = ImageIO.read(new File("./resources/9081706_zodiac_scorpio_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Sagitario";
                        img = ImageIO.read(new File("./resources/9081703_zodiac_sagittarius_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                case "12":
                    if (Integer.parseInt(s[0])<= 21){
                        signo = "Sagitario"; 
                        img = ImageIO.read(new File("./resources/9081703_zodiac_sagittarius_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                       panel2.add(foto, gbc2);
                    }
                    else {
                        signo = "Capricornio";
                        img = ImageIO.read(new File("./resources/9081714_zodiac_capricorn_icon.png"));
                        ImageIcon icon = new ImageIcon(img);
                        foto = new JLabel(icon);
                        panel2.add(foto, gbc2);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(this,"Introduce una fecha válida (dd-mm-aaaa)","Atención", JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
        }else {
          return false; 
        }
     }
        
    public void enviarActionPerformed(ActionEvent e) throws IOException{
        panel2.removeAll();
        comprobarNombre();
        comprobarFecha();   
        if (comprobarSigno()){  
            
            mensaje = new JTextArea(5, 5);
            mensaje.setText("Queride amigue " + nombreUsuario.getText() + ", tu signo zodiacal es " + signo + ". \n "
                    + "?Hay algo que te inquieta, te atormenta o te perturba?" + "\n" +"Elige el tema sobre el que necesitas saber tu predicción");
            mensaje.setEditable(false);
            mensaje.setBackground(Color.decode("#F7DBFF"));
            mensaje.setForeground(Color.BLACK);
            gbc2.insets = new Insets(5, 5, 5, 5); 
            gbc2.gridx = 1;
            gbc2.gridy = 0;
            gbc2.gridwidth = 4;
            gbc2.fill= GridBagConstraints.HORIZONTAL;
            panel2.add(mensaje, gbc2);
            
            asunto = new JComboBox(asuntos);
            asunto.setToolTipText("Despliega el menú y elige el tema de tu predicción");
            gbc2.gridx = 1;
            gbc2.gridy = 1;
            gbc2.gridwidth = 1;
            gbc2.fill= GridBagConstraints.HORIZONTAL;
            panel2.add(asunto, gbc2);
            
            JButton pred = new JButton("Saber predicción");
            pred.setToolTipText("Pulsa aquí para pedir una nueva predicción");
            pred.setMnemonic('p');
            pred.setBackground(new Color(128,0,128));
            pred.setForeground(Color.WHITE);
            gbc2.gridx = 2;
            gbc2.gridy = 1;
            gbc2.gridwidth = 1;
            gbc2.fill= GridBagConstraints.HORIZONTAL;
            panel2.add(pred, gbc2);
            
              
            pred.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                    predActionPerformed(e);
            }
          });
            panel2.revalidate();
            panel2.repaint();
            
         }   
    }
    
        public void predActionPerformed(ActionEvent e){
            panel3.removeAll();
            eleccion = asunto.getSelectedItem().toString(); 
            resultado = generarPrediccionHoroscopo(eleccion);
            JLabel p = new JLabel("Tu predicción es: ");
            gbc3.gridx = 0;
            gbc3.gridy = 2;
            panel3.add(p, gbc3);
            prediccion = new JTextArea();
            prediccion.setEditable(false);
            prediccion.setBackground(Color.decode("#F7DBFF"));
            prediccion.setForeground(Color.BLACK);
            prediccion.setText(resultado);
            gbc3.insets = new Insets(10, 5, 5, 5); 
            gbc3.gridx = 0;
            gbc3.gridy = 8;
            panel3.add(prediccion, gbc3);
            panel3.revalidate();
            panel3.repaint();
        }
    
    
        
        
        public static String generarPrediccionHoroscopo(String eleccion) {
            String[] frasesSalud = {
                "Cuida tu bienestar físico y mental hoy.\n ",
                "Experimentarás un aumento en la energía vital.\n ",
                "La alineación planetaria sugiere un enfoque renovado en la salud física.\n ",
                "Podrás beneficiarte de enfoques holísticos para la salud, como la meditación y la atención plena.\n ",
                "Experimentarás una mayor claridad mental este mes.\n ",
                "La influencia de Mercurio retrógrado favorece la comunicación abierta sobre la salud.\n ",
                "Podrás experimentar una sensación de equilibrio interior y fortaleza física.\n ",
                "Es un buen día para iniciar hábitos saludables.\n ",
                "La energía positiva favorecerá tu salud.\n "
            };
        String[] frasesTrabajo = {
            "Podrías recibir buenas noticias financieras hoy.\n ",
            "Sé cauteloso con tus gastos y toma decisiones financieras con sabiduría.\n ",
            "Te espera un período de estabilidad y logros laborales.\n ",
            "La suerte te acompa?ará durante los exámenes.\n ",
            "Mala semana para tener exámenes.\n ",
            "Estás en el centro de nuevas oportunidades profesionales este mes.\n ",
            "Puedes experimentar un aumento en la intuición y la creatividad en el trabajo.\n ",
            "Descatarás en la resolución de problemas y toma de decisiones en el trabajo.\n ",
            "Las inversiones podrían dar frutos inesperados.\n "
        };
        String[] frasesAmor = {
            "Momento propicio para fortalecer la relación con tu pareja.\n ",
            "Si estás soltero, podrías conocer a alguien especial hoy.\n ",
            "Podrás experimentar un período romántico excepcional.\n ",
            "Tendrás oportunidades para profundizar tus conexiones emocionales.\n ",
            "Sentirás un impulso a buscar nuevas aventuras y pasiones.\n ",
            "Encontrarás la estabilidad emocional que han estado buscando.\n ",
            "Experimentarás una fase de comunicación más profunda en tus relaciones amorosas.\n ",
            "Expresa tus sentimientos abiertamente y fortalecerás los lazos afectivos.\n "
        };
        if (eleccion.equalsIgnoreCase("salud")) {
            return obtenerFraseAleatoria(frasesSalud);
        } else if (eleccion.equalsIgnoreCase("trabajo")) {
            return obtenerFraseAleatoria(frasesTrabajo);
        } else if (eleccion.equalsIgnoreCase("amor")) {
            return obtenerFraseAleatoria(frasesAmor);
        } else if (eleccion.equalsIgnoreCase("todo")) {
            return obtenerFraseAleatoria(frasesSalud) + " " +
                   obtenerFraseAleatoria(frasesTrabajo) + " " +
                   obtenerFraseAleatoria(frasesAmor);
        } else {
            return "Asunto no válido. Por favor, elige entre 'salud', trabajo, 'amor' o 'todo'.";
        }
    }


    private static String obtenerFraseAleatoria(String[] frases) {
        Random rand = new Random();
        int indice = rand.nextInt(frases.length);
        return frases[indice];
    }
   
}
    

                
                

