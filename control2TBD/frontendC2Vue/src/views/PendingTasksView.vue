<script setup lang="ts">
import { onMounted, ref } from "vue";
import InputText from "primevue/inputtext";
import Button from "primevue/button";
import Dialog from "primevue/dialog";
import Calendar from "primevue/calendar";

const id = localStorage.getItem('idUser');
const token = localStorage.getItem('authToken');
const registerPost = async () => {
    if (token === null) { return }
    try {
        const response = await fetch(`http://localhost:8091/task/create`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': token,
            },
            body: JSON.stringify(state.value)
        });

        if (!response.ok) {
            throw new Error('Failed to register');
        }

        // Handle token and user data here, e.g., storing the token or redirecting the user
    } catch (error) {
        console.error('Registration error:', error);
    }
};

const errors = ref("")
async function onSubmit(event: Event) {
    event.preventDefault(); // Prevent default form submission
    try {
        state.value.expiredate.toISOString().split('T')[0];
        await registerPost();
        visible.value = false

    } catch (error) {
        errors.value = "Contraseña debe ser de 8 caracters"
        console.error('Validation error:', error);
        // Handle validation errors, e.g., show error messages in the UI
    }
}

const state = ref({
    title: '',
    description: '',
    expiredate: new Date(),
    active: true,
    user: { id: id },


})
const visible = ref(false)

const tasks = ref()

async function fetchTasks() {
    const id = localStorage.getItem('idUser');
    try {
        const response = await fetch(`http://localhost:8091/task/taskByUser/${id}`, { headers: { 'Authorization': token } });
        const result = await response.json()
        tasks.value = result; // Make sure to adjust this according to the actual structure of your response
    } catch (error) {
        console.error('There was an error fetching the user data:', error);
    }
}



onMounted(async () => { await fetchTasks() })
</script>
<template>
    <div class=" h-full flex flex-col items-center  align-middle gap-4 mt-12 ">



        <Button label="Crear nueva tarea" @click="visible = true" />
        <Dialog v-model:visible="visible" modal header="Crear Tarea" :style="{ width: '25rem' }">
            <span class="p-text-secondary block mb-5">Agregue la información</span>
            <div class="flex align-items-center gap-3 mb-3">
                <label for="titulo" class="font-semibold w-6rem">Titulo</label>
                <InputText v-model="state.title" id="titulo" class="flex-auto" autocomplete="off" />
            </div>
            <div class="flex align-items-center gap-3 mb-5">
                <label for="descripcion" class="font-semibold w-6rem">Descripción</label>
                <InputText v-model="state.description" id="descripcion" class="flex-auto" autocomplete="off" />
            </div>

            <div class="flex justify-content-end gap-2  ">
                <label for="expiredate" class="font-semibold w-6rem">Expiración</label>
                <Calendar id="expiredate" v-model="state.expiredate" dateFormat="dd/mm/yy" />
            </div>
            <div class="flex justify-content-end gap-2 mt-4">
                <Button type="button" label="Cancel" severity="secondary" @click="visible = false"></Button>
                <Button type="button" label="Save" @click="onSubmit"></Button>
            </div>
        </Dialog>
        <div v-if="tasks && tasks.length > 0">
            <div class=" bg-red-500" v-for="task in tasks" :key="task.id">
                <p>{{ task.title }}</p> <!-- Mustache syntax corrected from {task.title} to {{ task.title }} -->
            </div>
        </div>


    </div>
</template>
