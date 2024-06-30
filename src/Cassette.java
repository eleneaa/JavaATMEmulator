public class Cassette {
    int count10;
    int count50;
    int count100;

    //Конструктор
    public Cassette(int count10, int count50, int count100){
        this.count10 = count10;
        this.count50 = count50;
        this.count100 = count100;
    }

    //Метод для получения количества банкнот каждого номинала
    public int[] getCasseteStatys(){
        int[] cassetteStatus = new int[] {count10, count50, count100};
        return cassetteStatus;
    }

    //Метод для пополнения кассет
    public void refillCassette(int denomination, int count){
        switch (denomination){
            case 10:
                count10 += count;
                break;
            case 50:
                count50 += count;
                break;
            case 100:
                count100 += count;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + denomination);
        }
    }

    //Метод снятия с кассет
    public void removeFromCassette(int denomination, int count){
        switch (denomination){
            case 10:
                count10 -= count;
                break;
            case 50:
                count50 -= count;
                break;
            case 100:
                count100 -= count;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + denomination);
        }
    }
}
