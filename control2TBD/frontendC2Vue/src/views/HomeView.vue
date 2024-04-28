<script setup lang="ts">
import { onMounted, ref } from 'vue';

const token = localStorage.getItem('authToken');
const tasks = ref()
async function fetchTasks() {
    const id = localStorage.getItem('idUser');
    if (!token) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/taskByUser/${id}`, { headers: { 'Authorization': token } });
        const result = await response.json()
        tasks.value = result; // Make sure to adjust this according to the actual structure of your response
        getTasksExpiringSoon(result)
    } catch (error) {
        console.error('There was an error fetching the user data:', error);
    }
}

interface Task {
    id: number;
    title: string;
    expiredate: string;
    active: boolean;
    // Add other properties as needed
}

const expiringTasks = ref<Task[]>([])

function getTasksExpiringSoon(tasks: any) {
    const now = new Date();
    const oneDayLater = new Date(now.getTime() + 48 * 60 * 60 * 1000); // Adds 24 hours to the current time

    const expiringSoon = tasks.filter((task: any) => {
        const expirationDate = new Date(task.expiredate);
        return expirationDate > now && expirationDate < oneDayLater;
    });
    expiringTasks.value = expiringSoon
}




onMounted(async () => { await fetchTasks(); getTasksExpiringSoon(tasks) })
</script>
<template>
    <div class="h-svh flex flex-col justify-center align-middle items-center -my-32">
        <h1 class=" text-2xl">Bienvenido</h1>
        <h1 class=" text-2xl">Tienes {{ expiringTasks.length }} tarea que terminara pronto</h1>
        <div v-if="expiringTasks.length > 0">
            <div v-for="task in expiringTasks" :key="task.id">
                <h1 class=" text-xl">☉ {{ task.title as string }}</h1>
            </div>
        </div>
    </div>

</template>