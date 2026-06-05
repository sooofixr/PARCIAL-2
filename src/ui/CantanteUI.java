package ui;

import db.operaciones.CantanteDAO;
import model.Cantante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CantanteUI extends JFrame {

    private CantanteDAO dao;

    private JTextField txtNombre;
    private JTextField txtNacionalidad;
    private JTextField txtGeneroMusical;
    private JTextField txtEdad;
    private JTextField txtCancionReconocida;

    private JTextField txtIdBuscar;
    private JTextField txtValorFiltro;
    private JComboBox<String> comboFiltro;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    public CantanteUI() {
        dao = new CantanteDAO();

        setTitle("Sistema de Gestion de Cantantes");
        setSize(950, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        iniciarComponentes();
        cargarTodos();
    }

    private void iniciarComponentes() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(5, 2, 5, 5));

        txtNombre = new JTextField();
        txtNacionalidad = new JTextField();
        txtGeneroMusical = new JTextField();
        txtEdad = new JTextField();
        txtCancionReconocida = new JTextField();

        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Nacionalidad:"));
        panelFormulario.add(txtNacionalidad);

        panelFormulario.add(new JLabel("Genero musical:"));
        panelFormulario.add(txtGeneroMusical);

        panelFormulario.add(new JLabel("Edad:"));
        panelFormulario.add(txtEdad);

        panelFormulario.add(new JLabel("Cancion reconocida:"));
        panelFormulario.add(txtCancionReconocida);

        JPanel panelBotonesFormulario = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton btnGuardar = new JButton("Adicionar cantante");
        JButton btnLimpiar = new JButton("Limpiar campos");
        JButton btnConsultarTodos = new JButton("Consultar todos");

        panelBotonesFormulario.add(btnGuardar);
        panelBotonesFormulario.add(btnLimpiar);
        panelBotonesFormulario.add(btnConsultarTodos);

        JPanel panelSuperior = new JPanel(new BorderLayout(5, 5));
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotonesFormulario, BorderLayout.SOUTH);

        JPanel panelConsultas = new JPanel(new GridLayout(2, 1, 5, 5));

        JPanel panelBuscarId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtIdBuscar = new JTextField(10);
        JButton btnBuscarId = new JButton("Buscar por ID");

        panelBuscarId.add(new JLabel("ID:"));
        panelBuscarId.add(txtIdBuscar);
        panelBuscarId.add(btnBuscarId);

        JPanel panelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        comboFiltro = new JComboBox<>(new String[]{"Nacionalidad", "Genero Musical", "Nombre"});
        txtValorFiltro = new JTextField(18);
        JButton btnFiltrar = new JButton("Filtrar");

        panelFiltro.add(new JLabel("Filtrar por:"));
        panelFiltro.add(comboFiltro);
        panelFiltro.add(txtValorFiltro);
        panelFiltro.add(btnFiltrar);

        panelConsultas.add(panelBuscarId);
        panelConsultas.add(panelFiltro);

        JPanel panelArriba = new JPanel(new BorderLayout(5, 5));
        panelArriba.add(panelSuperior, BorderLayout.NORTH);
        panelArriba.add(panelConsultas, BorderLayout.SOUTH);

        String[] columnas = {
                "ID",
                "Nombre",
                "Nacionalidad",
                "Genero Musical",
                "Edad",
                "Cancion Reconocida",
                "Registrado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tabla);

        panelPrincipal.add(panelArriba, BorderLayout.NORTH);
        panelPrincipal.add(scrollTabla, BorderLayout.CENTER);

        add(panelPrincipal);

        btnGuardar.addActionListener(e -> insertarCantante());
        btnLimpiar.addActionListener(e -> limpiarCampos());
        btnConsultarTodos.addActionListener(e -> cargarTodos());
        btnBuscarId.addActionListener(e -> consultarPorId());
        btnFiltrar.addActionListener(e -> filtrar());
    }

    private void insertarCantante() {
        String nombre = txtNombre.getText().trim();
        String nacionalidad = txtNacionalidad.getText().trim();
        String generoMusical = txtGeneroMusical.getText().trim();
        String edadTexto = txtEdad.getText().trim();
        String cancionReconocida = txtCancionReconocida.getText().trim();

        if (nombre.isEmpty() || nacionalidad.isEmpty() || generoMusical.isEmpty() || edadTexto.isEmpty() || cancionReconocida.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");
            return;
        }

        int edad;

        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un numero.");
            return;
        }

        Cantante c = new Cantante(nombre, nacionalidad, generoMusical, edad, cancionReconocida);
        dao.insertarCantante(c);

        JOptionPane.showMessageDialog(this, "Cantante adicionado con exito.");

        limpiarCampos();
        cargarTodos();
    }

    private void cargarTodos() {
        List<Cantante> lista = dao.consultarTodos();
        cargarTabla(lista);
    }

    private void consultarPorId() {
        String idTexto = txtIdBuscar.getText().trim();

        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID.");
            return;
        }

        int id;

        try {
            id = Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un numero.");
            return;
        }

        Cantante cantanteEncontrado = dao.consultarUnRegistro(id);

        modeloTabla.setRowCount(0);

        if (cantanteEncontrado != null) {
            agregarFila(cantanteEncontrado);
            llenarCampos(cantanteEncontrado);
        } else {
            JOptionPane.showMessageDialog(this, "No existe ningun cantante con ese ID.");
        }
    }

    private void filtrar() {
        String valorBusqueda = txtValorFiltro.getText().trim();

        if (valorBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor para filtrar.");
            return;
        }

        String opcion = comboFiltro.getSelectedItem().toString();
        String columnaSeleccionada = "";

        if (opcion.equals("Nacionalidad")) {
            columnaSeleccionada = "nacionalidad";
        } else if (opcion.equals("Genero Musical")) {
            columnaSeleccionada = "generomusical";
        } else if (opcion.equals("Nombre")) {
            columnaSeleccionada = "nombre";
        }

        List<Cantante> listaFiltrada = dao.filtrarPorCriterio(columnaSeleccionada, valorBusqueda);
        cargarTabla(listaFiltrada);

        if (listaFiltrada.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron cantantes con ese criterio.");
        }
    }

    private void cargarTabla(List<Cantante> lista) {
        modeloTabla.setRowCount(0);

        for (Cantante c : lista) {
            agregarFila(c);
        }
    }

    private void agregarFila(Cantante c) {
        modeloTabla.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getNacionalidad(),
                c.getGeneromusical(),
                c.getEdad(),
                c.getCancionreconocida(),
                c.getRegistrado()
        });
    }

    private void llenarCampos(Cantante c) {
        txtNombre.setText(c.getNombre());
        txtNacionalidad.setText(c.getNacionalidad());
        txtGeneroMusical.setText(c.getGeneromusical());
        txtEdad.setText(String.valueOf(c.getEdad()));
        txtCancionReconocida.setText(c.getCancionreconocida());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtNacionalidad.setText("");
        txtGeneroMusical.setText("");
        txtEdad.setText("");
        txtCancionReconocida.setText("");
    }
}