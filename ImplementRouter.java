class Router {

    private final Queue<int[]> queue;
    private final int maxMemory;
    private final Map<Integer, Destination> destinations;

    public Router(int memoryLimit) {
        queue = new LinkedList<>();  
        maxMemory = memoryLimit;
        destinations = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Destination dest = destinations.get(destination);
        if (dest != null && dest.contains(timestamp, source)) {
            return false;
        }

        if (queue.size() == maxMemory) {
            int[] firstPacket = queue.poll();
            Destination dest1 = destinations.get(firstPacket[1]);
            dest1.removePacket();
        }

        if (dest == null) {
            dest = new Destination();
            destinations.put(destination, dest);
        }
        dest.addPacket(timestamp, source);

        queue.add(new int[] { source, destination, timestamp });
        return true;
    }

    public int[] forwardPacket() {
        if (queue.isEmpty()) return new int[] {};

        int[] firstPacket = queue.poll();
        Destination dest1 = destinations.get(firstPacket[1]);
        dest1.removePacket();
        return new int[] { firstPacket[0], firstPacket[1], firstPacket[2] };
    }

    public int getCount(int destination, int startTime, int endTime) {
        Destination dest = destinations.get(destination);
        if (dest == null) return 0;
        return dest.getCount(startTime, endTime);
    }
}

class Destination {
    private static final long MULTIPLIER = 1000000L;

    private int start = -1, end = -1;
    private final List<int[]> packets;
    private final Set<Long> packetSet;

    Destination() {
        packets = new ArrayList<>();
        packetSet = new HashSet<>();
    }

    public void addPacket(int timestamp, int source) {
        if (start == -1) {
            start = 0;
        }

        if (packets.size() > end + 1) {
            packets.set(end + 1, new int[] { timestamp, source });
            end++;
        } else {
            packets.add(new int[] { timestamp, source });
            end++;
        }

        long x = (long) timestamp * MULTIPLIER + source;
        packetSet.add(x);
    }

    public void removePacket() {
        long x = (long) packets.get(start)[0] * MULTIPLIER + packets.get(start)[1];
        packetSet.remove(x);
        if (start == end) {
            start = -1;
            end = -1;
        } else {
            start++;
        }
    }

    public int getCount(int startTime, int endTime) {
        if (start == -1)
            return 0;

        int lb = end + 1;
        int ub = -1;
        int tempStart = start;
        int tempEnd = end;

        while (tempStart <= tempEnd) {
            int mid = tempStart + (tempEnd - tempStart) / 2;
            int x = packets.get(mid)[0];

            if (x >= startTime) {
                lb = mid;
                tempEnd = mid - 1;
            } else {
                tempStart = mid + 1;
            }
        }

        tempStart = start;
        tempEnd = end;
        while (tempStart <= tempEnd) {
            int mid = tempStart + (tempEnd - tempStart) / 2;
            int x = packets.get(mid)[0];
            if (x <= endTime) {
                ub = mid;
                tempStart = mid + 1;
            } else {
                tempEnd = mid - 1;
            }
        }

        if (lb <= ub) {
            return ub - lb + 1;
        }
        return 0;
    }

    public boolean contains(int timestamp, int source) {
        if (start == -1) return false;
        long key = (long) timestamp * MULTIPLIER + source;
        return packetSet.contains(key);
    }
}
