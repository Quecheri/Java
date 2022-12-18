/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;


import javax.swing.table.AbstractTableModel;

/**
 * Class contains model for our table
 * @author Quecheri
 * @version 1.1
 */
public class TableModel extends AbstractTableModel {
    	/** naglowki kolumn */
        private String[] columnNames = {"Roman",
                                        "Arabic"};
        /** dane zawarte w tabeli */
        private Object[][] data = {
            {"I",1},{"II",2},{"III",3},
            {"V",5},{"VI",6},{"VII",7},
            {"X",10},{"XX",20},{"XXIX",29},
            {"L",50},{"LX",60},{"LXX",70},
            {"C",100},{"CC",200},{"CCL",250},
            {"D",500} ,{"DX",510},{"DCC",700},
            {"M",1000},{"MM",2000},{"MMM",3000},
            {"MMMCM",3900},{"MMMCMXCIX",3999}
        };
        
		/**
                 * @return ilosc kolumn w tabeli
                 */
        public int getColumnCount() {
            return columnNames.length;
        }
		
		/** @return ilosc pozycji w tabeli */
        public int getRowCount() {
            return data.length;
        }

		/** @return tytul kolumny o numerze <code>col</code> 
		 *  @param col nr kolumny 
		 */
        public String getColumnName(int col) {
            return columnNames[col];
        }

		/** @return zawartosc wybranej komorki w tabeli 
		 *	@param row nr wiersza wybranej komorki
		 *	@param col nr kolumny wybranej komorki
		 */
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        /**	metoda uzyta do okreslenia domyslnego sposobu edycji i
         *	prezentacji dla kazdej z komorek
         *	gdyby nie zostala zaimplementowana ostatnia kolumna 
         *	zawieralaby tekst ("true"/"false") zamiast checkbox'ow
         *	@return klasa wybranej kolumny
         *	@param c nr wybranej kolumny
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /**	metoda implementowana gdy wystepuja
         *	komorki o zawartosci mozliwej do edycji
         * 	@return true gdy wybrana komorke mozna edytowac, false gdy nie mozna
         *	@param row nr wiersza wybranej komorki
         *	@param col nr kolumny wybranej komorki
         */
        public boolean isCellEditable(int row, int col) {
           return false;
        }

        /** metoda implementowana gdy dane w tabeli moga sie zmieniac
         *	@param value inserted into table
         *	@param row nr wiersza wybranej komorki
         *	@param col nr kolumny wybranej komorki
         */
        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }             
    }
