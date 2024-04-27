<script setup lang="ts">
import type { MenuItem } from 'primevue/menuitem';
import TabMenu from 'primevue/tabmenu';
import 'primeicons/primeicons.css'

const items: MenuItem[] = [
  {
    label: "Inicio",
    icon: "pi pi-home",
    url: '/'

  },
  {
    label: "Tareas Pendientes",
    icon: "pi pi-list",
    url: '/tasks'

  },
  {
    label: "Tareas Finalizadas",
    icon: "pi pi-list-check",
    url: '/pending'

  },
]
</script>

<template>
  <div class="h-lvh overflow-hidden">
    <header>
      <TabMenu :model="items" />
    </header>
    <RouterView />
  </div>
</template>
