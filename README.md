# Adaptable Priority Queue Using a Binary Heap

## Overview

This project was developed for COMP 352 (Data Structures and Algorithms) at Concordia University.

I implemented an adaptable priority queue in Java using a binary heap. The project explores how priority queues maintain their ordering when elements are inserted, removed, or modified.

## Features

- Insert and remove key-value entries
- Access and remove the highest-priority entry
- Switch between min-heap and max-heap ordering
- Replace the key or value of an existing entry
- Retrieve entries by their priority ranking
- Automatically resize the underlying array
- Merge two priority queues

## Implementation

The priority queue uses an array-based binary heap with generic key-value entries.

The implementation includes up-heap and down-heap operations to maintain heap ordering, as well as a heap-rebuilding method when switching between min-heap and max-heap modes.

## Files

- `APQ.java` — Priority queue implementation
- `APQEntry.java` — Generic key-value entry class
- `MainAPQTest.java` — Demonstration and testing of priority queue operations

## Technologies

Java, object-oriented programming, binary heaps, and data structures.

## Project Context

Undergraduate programming assignment completed for COMP 352 at Concordia University.# java-adaptable-priority-queue
Java implementation of an adaptable priority queue using a binary heap, developed for a Data Structures and Algorithms course.
