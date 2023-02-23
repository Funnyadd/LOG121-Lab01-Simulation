package model;

public class UsineInput extends Input {

        private final int maxQuantity;
        private int quantity;

        public UsineInput(MachineComponent component, int maxQuantity) {
                super(component);
                this.maxQuantity = maxQuantity;
                this.quantity = 0;
        }

        public int getMaxQuantity() {
                return maxQuantity;
        }

        public int getQuantity() {
                return quantity;
        }

        public void addQuantity() {
                this.quantity += 1;
        }

        public void resetQuantity() {
                this.quantity -= maxQuantity;
        }

        @Override
        public String toString() {
                return "UsineInput{" +
                        super.toString() +
                        ", quantity='" + quantity + '\'' +
                        '}';
        }
}
