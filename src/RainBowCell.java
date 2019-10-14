public class RainBowCell implements Comparable<RainBowCell>{
    private Integer index;
    private Long i2i_value;

    public RainBowCell(int index, long i2i_value) {
        this.index = index;
        this.i2i_value = i2i_value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Long getI2i_value() {
        return i2i_value;
    }

    public void setI2i_value(long i2i_value) {
        this.i2i_value = i2i_value;
    }

    @Override
    public int compareTo(RainBowCell o) {
        return this.getI2i_value().compareTo(o.getI2i_value());
    }
}
