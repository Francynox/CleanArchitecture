package entities

import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4

abstract class UUIDEntity(
    val id: Uuid = uuid4(),
)
