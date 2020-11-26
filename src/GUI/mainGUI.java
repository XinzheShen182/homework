package GUI;

import Bean.DBBean;
import op.returnVector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class mainGUI extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JTextField textField_2;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table_2;
    private JTable table_3;
    private JTable table_4;
    private JTable table_5;
    private JTable table_6;
    private JTable table_7;
    private JTable table_9;
    private JTable table_8;
    private JTable table_10;
    private JTable table_IncomingGoodsList;
    private JTextField totalPriceDisplay;
    private DBBean db=new DBBean();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    mainGUI frame = new mainGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * @throws SQLException
     */
    public mainGUI() throws SQLException {
        // 连接数据库
        //this.db = new DBBean();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 794, 510);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        contentPane.add(tabbedPane);

        // 商品
        JPanel panel = new JPanel();
        tabbedPane.addTab("\u8D27\u54C1", null, panel, null);
        panel.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);
        Vector<Object> name = new Vector<Object>();
        name.add("id");
        name.add("name");
        name.add("outprice");
        Vector<Vector<Object>> data = returnVector.FromDBReadAll(db,"itemmanager",name);
        DefaultTableModel tablemodel = new DefaultTableModel(data, name);
        table = new JTable();
        table.setModel(tablemodel);
        scrollPane.setViewportView(table);


        // 客户
        JPanel panel_1 = new JPanel();
        tabbedPane.addTab("\u5BA2\u6237", null, panel_1, null);
        panel_1.setLayout(new BorderLayout(0, 0));

        // 新增用户按钮
        JButton btnNewButton_1 = new JButton("\u65B0\u589E\u5BA2\u6237");
        panel_1.add(btnNewButton_1, BorderLayout.NORTH);

        // 用户列表
        JScrollPane scrollPane_1 = new JScrollPane();
        panel_1.add(scrollPane_1, BorderLayout.CENTER);
        Vector<Object> name_1 = returnVector.getHeadName(db,"customermanager");
        Vector<Vector<Object>> data_1 = returnVector.FromDBReadAll(db,"customermanager",name_1);
        DefaultTableModel tablemodel_1 = new DefaultTableModel(data_1, name_1);
        table_1 = new JTable();
        table_1.setModel(tablemodel_1);
        scrollPane_1.setViewportView(table_1);

        // 新增用户按钮绑定
        btnNewButton_1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // 弹出对话框
                windowsToCreateCustomer window_2 = new windowsToCreateCustomer(db, table_1);
                window_2.setVisible(true);
                window_2.setSize(400, 400);

            }
        });


        //销售
        JPanel panel_2 = new JPanel();
        tabbedPane.addTab("\u9500\u552E", null, panel_2, null);
        panel_2.setLayout(new BorderLayout(0, 0));

        //二级选项卡
        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        panel_2.add(tabbedPane_1, BorderLayout.CENTER);

        //开销售单
        JPanel panel_6 = new JPanel();
        tabbedPane_1.addTab("\u5F00\u9500\u552E\u5355", null, panel_6, null);
        panel_6.setLayout(new BorderLayout(0, 0));
        Box horizontalBox = Box.createHorizontalBox();
        panel_6.add(horizontalBox, BorderLayout.NORTH);

        // 客户确认按钮  显示的label
        JLabel lblNewLabel_2 = new JLabel("\u5BA2\u6237");
        horizontalBox.add(lblNewLabel_2);
        // 客户确认按钮  输入客户编号的文本框，通过姓名查
        textField_2 = new JTextField();
        textField_2.setText("input name");
        textField_2.setColumns(10);
        horizontalBox.add(textField_2);
        // 客户确认按钮  按钮大小等属性
        JButton btnNewButton_2_1 = new JButton("确认");
        horizontalBox.add(btnNewButton_2_1);

        Component horizontalStrut_1 = Box.createHorizontalStrut(450);
        horizontalBox.add(horizontalStrut_1);

        // 查找到的客户 label
        JLabel lblNewLabel_1_1 = new JLabel("查找到的客户");
        horizontalBox.add(lblNewLabel_1_1);
        // 查找到的客户 客户名的打印的textfield
        textField = new JTextField();
        textField.setEnabled(false);
        textField.setEditable(false);
        horizontalBox.add(textField);
        textField.setColumns(10);

        // 客户确认按钮  按钮绑定鉴定器
        btnNewButton_2_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet res = db.executeFind(textField_2.getText(), "customermanager", "name");
                try {
                    if (res.next()){
                        textField_2.setText("");
                        textField.setText(res.getString("name"));
                    }
                    else {
                        JTextArea aboutarea = new JTextArea();
                        aboutarea.setText("找不到用户！\n");
                        JOptionPane.showConfirmDialog(null,aboutarea,"Error!",JOptionPane.PLAIN_MESSAGE);
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        JPanel panel_10 = new JPanel();
        panel_6.add(panel_10, BorderLayout.SOUTH);
        panel_10.setLayout(new BorderLayout(0, 0));
        // 新增的按钮
        JButton btnNewButton_2 = new JButton("\u65B0\u589E");
        btnNewButton_2.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_2, BorderLayout.NORTH);
        // 保存按钮
        JButton btnNewButton_3 = new JButton("\u4FDD\u5B58");
        btnNewButton_3.setFont(new Font("����", Font.PLAIN, 16));
        panel_10.add(btnNewButton_3, BorderLayout.EAST);

        Box horizontalBox_1 = Box.createHorizontalBox();
        panel_10.add(horizontalBox_1, BorderLayout.WEST);

        // 共计的label
        JLabel lblNewLabel = new JLabel("    \u5171\u8BA1\uFF1A");
        lblNewLabel.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel);
        // 共计的显示textfield
        textField_1 = new JTextField();
        horizontalBox_1.add(textField_1);
        textField_1.setColumns(10);
        // 共计的单位“元“显示
        JLabel lblNewLabel_1 = new JLabel("\u5143");
        lblNewLabel_1.setFont(new Font("����", Font.PLAIN, 16));
        horizontalBox_1.add(lblNewLabel_1);

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_6.add(scrollPane_2, BorderLayout.CENTER);

        // 显示添加的物品
        Vector<Object> name_2 = new Vector<Object>();
        name_2.add("name"); name_2.add("num"); name_2.add("price/one"); name_2.add("price/all");
        Vector<Vector<Object>> data_2 = new Vector<>();
        DefaultTableModel tableModel_2 = new DefaultTableModel(data_2, name_2);
        table_2 = new JTable();
        table_2.setModel(tableModel_2);
        scrollPane_2.setViewportView(table_2);

        // 新增按钮的button绑定
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                windowsToCreateOrders window_1 = new windowsToCreateOrders(db, table_2, data_2, name_2);
                window_1.setVisible(true);
                window_1.setSize(400, 400);
            }
        });


        Vector<Object> order_name = returnVector.getHeadName(db,"ordermanager");
        Vector<Object> order_name2 = returnVector.getHeadName(db,"ordermanager");
        order_name2.add("stateChange");
        //待审核
        JPanel panel_5 = new JPanel();
        tabbedPane_1.addTab("待审核", null, panel_5, null);
        panel_5.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_3 = new JScrollPane();
        panel_5.add(scrollPane_3, BorderLayout.CENTER);
        Vector<Vector<Object>> data_3 = returnVector.FromDBRead(db, "ordermanager", order_name, "1", "State");
        DefaultTableModel tablemodel_3 = new DefaultTableModel(data_3, order_name2);
        table_3 = new JTable();
        table_3.setModel(tablemodel_3);
        scrollPane_3.setViewportView(table_3);
        TableColumn tcm = table_3.getColumnModel().getColumn(order_name2.size()-1);
        tcm.setCellEditor(table_3.getDefaultEditor(Boolean.class));
        tcm.setCellRenderer(table_3.getDefaultRenderer(Boolean.class));
        tablemodel_3.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println(e.getFirstRow()+"''''''''");
                if(e.getColumn()==order_name2.size()-1){
                    System.out.println(data_3.get(e.getFirstRow()));
                    db.executeUpdate((String)(data_3.get(e.getFirstRow()).get(0))
                            ,"ordermanager","ID","2","State");
                }
            }
        });


        //待收款
        JPanel panel_7 = new JPanel();
        tabbedPane_1.addTab("待收款", null, panel_7, null);
        panel_7.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_4 = new JScrollPane();
        panel_7.add(scrollPane_4, BorderLayout.CENTER);
        Vector<Vector<Object>> data_4 = returnVector.FromDBRead(db, "ordermanager", order_name, "2", "State");
        DefaultTableModel tablemodel_4 = new DefaultTableModel(data_4, order_name2);
        table_4 = new JTable();
        table_4.setModel(tablemodel_4);
        scrollPane_4.setViewportView(table_4);
        TableColumn tcm2 = table_4.getColumnModel().getColumn(order_name2.size()-1);
        tcm2.setCellEditor(table_4.getDefaultEditor(Boolean.class));
        tcm2.setCellRenderer(table_4.getDefaultRenderer(Boolean.class));
        tablemodel_4.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn()==order_name2.size()-1){
                    System.out.println(data_4.get(e.getFirstRow()));
                    db.executeUpdate((String)(data_4.get(e.getFirstRow()).get(0))
                            ,"ordermanager","ID","3","State");
                }
            }
        });


        //待退货
        JPanel panel_8 = new JPanel();
        tabbedPane_1.addTab("\u9000\u8D27", null, panel_8, null);
        panel_8.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_5 = new JScrollPane();
        panel_8.add(scrollPane_5, BorderLayout.CENTER);
        Vector<Vector<Object>> data_5 = returnVector.FromDBRead(db, "ordermanager", order_name, "3", "State");
        DefaultTableModel tablemodel_5 = new DefaultTableModel(data_5, order_name2);
        table_5 = new JTable();
        table_5.setModel(tablemodel_5);
        scrollPane_5.setViewportView(table_5);
        TableColumn tcm3 = table_5.getColumnModel().getColumn(order_name2.size()-1);
        tcm3.setCellEditor(table_5.getDefaultEditor(Boolean.class));
        tcm3.setCellRenderer(table_5.getDefaultRenderer(Boolean.class));
        tablemodel_5.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn()==order_name2.size()-1){
                    System.out.println(data_5.get(e.getFirstRow()));
                    db.executeUpdate((String)(data_5.get(e.getFirstRow()).get(0))
                            ,"ordermanager","ID","4","State");
                }
            }
        });

        //已完成
        JPanel panel_12 = new JPanel();
        tabbedPane_1.addTab("已完成", null, panel_12, null);
        panel_12.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_6 = new JScrollPane();
        panel_12.add(scrollPane_6, BorderLayout.CENTER);
        Vector<Vector<Object>> data_6 = returnVector.FromDBRead(db, "ordermanager", order_name, "4", "State");
        DefaultTableModel tablemodel_6 = new DefaultTableModel(data_6, order_name2);
        table_6 = new JTable();
        table_6.setModel(tablemodel_6);
        scrollPane_6.setViewportView(table_6);
        TableColumn tcm4 = table_6.getColumnModel().getColumn(order_name2.size()-1);
        tcm4.setCellEditor(table_6.getDefaultEditor(Boolean.class));
        tcm4.setCellRenderer(table_6.getDefaultRenderer(Boolean.class));
        tablemodel_6.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if(e.getColumn()==order_name2.size()-1){
                    System.out.println(data_6.get(e.getFirstRow()));
                    db.executeUpdate((String)(data_6.get(e.getFirstRow()).get(0))
                            ,"ordermanager","ID","4","State");
                }
            }
        });

        //订单列表
        JPanel panel_4 = new JPanel();
        tabbedPane_1.addTab("订单列表", null, panel_4, null);
        panel_4.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_8 = new JScrollPane();
        panel_4.add(scrollPane_8, BorderLayout.CENTER);
        Vector<Vector<Object>> data_8 = returnVector.FromDBReadAll(db,"ordermanager",order_name);
        DefaultTableModel tablemodel_8 = new DefaultTableModel(data_8, order_name);
        table_8 = new JTable();
        table_8.setModel(tablemodel_8);
        scrollPane_8.setViewportView(table_8);

        //查看销售单
        JPanel panel_9 = new JPanel();
        tabbedPane_1.addTab("\u67E5\u770B\u9500\u552E\u5355", null, panel_9, null);
        panel_9.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_7 = new JScrollPane();
        panel_9.add(scrollPane_7, BorderLayout.CENTER);
        table_6 = new JTable();
        scrollPane_7.setViewportView(table_6);


        // 库存
        JPanel panel_3 = new JPanel();
        tabbedPane.addTab("\u5E93\u5B58", null, panel_3, null);
        panel_3.setLayout(new BorderLayout(0, 0));
        JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.TOP);
        panel_3.add(tabbedPane_2, BorderLayout.CENTER);

        // 进货
        JPanel panel_AddIncomingOrders = new JPanel();
        tabbedPane_2.addTab("进货", null, panel_AddIncomingOrders, null);
        panel_AddIncomingOrders.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_IncomingGoodsList = new JScrollPane();
        panel_AddIncomingOrders.add(scrollPane_IncomingGoodsList, BorderLayout.CENTER);

        // 进货列表的tablemodel
        Vector<String> name_7 = new Vector<String>();
        name_7.add("货品");
        name_7.add("单价");
        name_7.add("价格");
        name_7.add("总价");
        Vector<Vector<Object>> data_7 = new Vector<Vector<Object>>();
        DefaultTableModel tablemodel_7 = new DefaultTableModel(data_7, name_7);

        // 进货列表的table
        table_IncomingGoodsList = new JTable();
        table_IncomingGoodsList.setModel(tablemodel_7);
        scrollPane_IncomingGoodsList.setViewportView(table_IncomingGoodsList);

        JPanel panel_11 = new JPanel();
        panel_AddIncomingOrders.add(panel_11, BorderLayout.SOUTH);
        panel_11.setLayout(new BorderLayout(0, 0));

        // 新增货物按钮
        JButton btnAddIncomingGoods = new JButton("+ 新增货物");
        panel_11.add(btnAddIncomingGoods, BorderLayout.NORTH);
        btnAddIncomingGoods.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                windowsToCreateItem window_3 = new windowsToCreateItem();
                window_3.setVisible(true);
                window_3.setSize(400, 400);

            }

        });

        // 保存按钮
        JButton btnSave = new JButton("保存");
        panel_11.add(btnSave, BorderLayout.EAST);

        Box horizontalBox_totalIncomingPrice = Box.createHorizontalBox();
        panel_11.add(horizontalBox_totalIncomingPrice, BorderLayout.WEST);

        // 合计按钮
        JLabel lblNewLabel_3 = new JLabel("合计：");
        horizontalBox_totalIncomingPrice.add(lblNewLabel_3);
        // 合计显示价格的TextField
        totalPriceDisplay = new JTextField();
        totalPriceDisplay.setEnabled(false);
        totalPriceDisplay.setEditable(false);
        horizontalBox_totalIncomingPrice.add(totalPriceDisplay);
        totalPriceDisplay.setColumns(10);
        // 合计显示价格的单位
        JLabel lblNewLabel_4 = new JLabel("元");
        horizontalBox_totalIncomingPrice.add(lblNewLabel_4);


        // 清点
        JPanel panel_13 = new JPanel();
        tabbedPane_2.addTab("清点", null, panel_13, null);
        panel_13.setLayout(new BorderLayout(0, 0));
        JScrollPane scrollPane_10 = new JScrollPane();
        panel_13.add(scrollPane_10, BorderLayout.CENTER);
        Vector<Object> name_10 = new Vector<>();
        name_10.add("id");
        name_10.add("name");
        name_10.add("num");
        Vector<Vector<Object>> data_10 = returnVector.FromDBReadAll(db,"itemmanager",name_10);
        DefaultTableModel tablemodel_10 = new DefaultTableModel(data_10, name_10);
        table_10 = new JTable();
        table_10.setModel(tablemodel_10);
        scrollPane_10.setViewportView(table_10);


    }
}
