/*

Enum is used here because the task categories are predefined.
Adds security and type safety to the code, as only valid categories can be assigned to tasks.
 */
package model;

public enum TaskCategory {
    HOMEWORK,
    CHORE,
    FUN,
    WORK,
    OTHER
}
