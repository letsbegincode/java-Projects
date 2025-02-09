# Consistent Hashing in Java

## Overview
This project implements **consistent hashing** in Java, a technique used for efficient request/data distribution across servers in scalable distributed systems. It minimizes key remapping when servers are added or removed, solving the rehashing problem found in traditional modulo-based hashing.

## Features
- Implements **consistent hashing** with a **hash ring**
- Supports **dynamic addition and removal of servers**
- Uses **SHA-1 for uniform hash distribution**
- Includes **virtual nodes** to balance load distribution
- Efficient key lookup using a **clockwise traversal** method

## How It Works
1. **Hash Ring Creation:** Servers and keys are mapped to a circular hash space using SHA-1.
2. **Server Lookup:** To find a key’s assigned server, we traverse clockwise on the ring until a server is found.
3. **Adding a Server:** Only a small fraction of keys are reassigned to the new server.
4. **Removing a Server:** Affected keys are reassigned to the next available server.
5. **Virtual Nodes:** Each server is mapped multiple times on the ring to balance data distribution.

## Installation
Clone the repository and compile the Java program:
```sh
javac ConsistentHashing.java
java ConsistentHashing
```

## Usage
Modify the `main` function to add or remove servers dynamically and observe the key distributions.


