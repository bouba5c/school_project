#ifndef POSITION_H
#define POSITION_H

/**
 * @class Position
 * @brief Represents a 2D position with row and column .
 */
class Position
{
public:
    /**
     * @brief Constructor for the Position class.
     * @param x The row  of the position.
     * @param y The column  of the position.
     */
    Position(int x, int y);

    /**
     * @brief Get the row  of the position.
     * @return The row coordinate.
     */
    int getRow();

    /**
     * @brief Get the column of the position.
     * @return The column coordinate.
     */
    int getColumn();

private:
    int row;
    int col;
};

#endif // POSITION_H
