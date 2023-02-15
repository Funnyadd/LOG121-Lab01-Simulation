package model;

public class UsineInput extends Input {

        private String quantity;

        public UsineInput(MachineComponent component, String quantity) {
                super(component);
                this.quantity = quantity;
        }

        public String getQuantity() {
                return quantity;
        }

        public void setQuantity(String quantity) {
                this.quantity = quantity;
        }

        @Override
        public String toString() {
                return "UsineInput{" +
                        super.toString() +
                        ", quantity='" + quantity + '\'' +
                        '}';
        }
}
