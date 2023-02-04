package model;

public class UsineInput extends Input {

        private String quantity;

        public UsineInput(String material, String quantity) {
                super(material);
                this.quantity = quantity;
        }

        public String getQuantity() {
                return quantity;
        }

        public void setQuantity(String quantity) {
                this.quantity = quantity;
        }
}
