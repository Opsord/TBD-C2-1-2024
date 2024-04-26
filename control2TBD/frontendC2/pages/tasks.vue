<script setup lang="ts">
const isOpen = ref(false)
</script>

<template>
    <div class=" h-svh flex flex-col items-center gap-y-8">
        <div class=" justify-start mt-8">
            <UButton label="Agregar nueva tarea" @click="isOpen = true" />

            <UModal v-model="isOpen">
                <CreateTask />
            </UModal>
        </div>
        <div class=" flex flex-row gap-4">
            <TaskCard />
            <TaskCard />
            <TaskCard />
        </div>
    </div>
</template>
