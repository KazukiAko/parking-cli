CREATE TABLE IF NOT EXISTS reservations (
    id BIGSERIAL PRIMARY KEY,
    spot_code VARCHAR(20) NOT NULL,
    vehicle_number VARCHAR(30) NOT NULL,
    reserved_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_reservations_reserved_date
    ON reservations (reserved_date);