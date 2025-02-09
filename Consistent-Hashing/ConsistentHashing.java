import java.util.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

class ConsistentHashing {
    private final SortedMap<Long, String> hashRing = new TreeMap<>();
    private final int virtualNodes;
    private final List<String> realNodes;

    public ConsistentHashing(List<String> servers, int virtualNodes) {
        this.virtualNodes = virtualNodes;
        this.realNodes = new ArrayList<>(servers);
        for (String server : servers) {
            addServer(server);
        }
    }

    private long hash(String key) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] digest = md.digest(key.getBytes());
            return ((long) (digest[0] & 0xFF) << 24) | ((long) (digest[1] & 0xFF) << 16) |
                   ((long) (digest[2] & 0xFF) << 8) | ((long) (digest[3] & 0xFF));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void addServer(String server) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hash(server + "#" + i);
            hashRing.put(hash, server);
        }
    }

    public void removeServer(String server) {
        for (int i = 0; i < virtualNodes; i++) {
            long hash = hash(server + "#" + i);
            hashRing.remove(hash);
        }
    }

    public String getServer(String key) {
        if (hashRing.isEmpty()) {
            return null;
        }
        long hash = hash(key);
        SortedMap<Long, String> tailMap = hashRing.tailMap(hash);
        long nodeHash = tailMap.isEmpty() ? hashRing.firstKey() : tailMap.firstKey();
        return hashRing.get(nodeHash);
    }

    public static void main(String[] args) {
        List<String> servers = Arrays.asList("Server1", "Server2", "Server3", "Server4");
        ConsistentHashing ch = new ConsistentHashing(servers, 3);

        String[] keys = {"Key1", "Key2", "Key3", "Key4", "Key5", "Key6"};
        for (String key : keys) {
            System.out.println("Key: " + key + " is mapped to Server: " + ch.getServer(key));
        }

        System.out.println("\nAdding Server5...");
        ch.addServer("Server5");
        for (String key : keys) {
            System.out.println("Key: " + key + " is mapped to Server: " + ch.getServer(key));
        }

        System.out.println("\nRemoving Server2...");
        ch.removeServer("Server2");
        for (String key : keys) {
            System.out.println("Key: " + key + " is mapped to Server: " + ch.getServer(key));
        }
    }
}
